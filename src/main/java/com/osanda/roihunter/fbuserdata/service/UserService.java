package com.osanda.roihunter.fbuserdata.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.osanda.roihunter.fbuserdata.exception.FacebookException;
import com.osanda.roihunter.fbuserdata.exception.UserAlreadyExsitsException;
import com.osanda.roihunter.fbuserdata.model.Album;
import com.osanda.roihunter.fbuserdata.model.Photo;
import com.osanda.roihunter.fbuserdata.model.User;
import com.osanda.roihunter.fbuserdata.model.dto.FacebookResponse;
import com.osanda.roihunter.fbuserdata.model.dto.ImageDto;
import com.osanda.roihunter.fbuserdata.model.dto.Payload;
import com.osanda.roihunter.fbuserdata.model.dto.Photos;
import com.osanda.roihunter.fbuserdata.model.dto.PictureData;
import com.osanda.roihunter.fbuserdata.model.enums.Gender;
import com.osanda.roihunter.fbuserdata.repository.AlbumRepository;
import com.osanda.roihunter.fbuserdata.repository.PhotoRepository;
import com.osanda.roihunter.fbuserdata.repository.UserRepository;
import com.osanda.roihunter.fbuserdata.util.DirectoryUtil;
import com.osanda.roihunter.fbuserdata.util.ReponseMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/***
 * user related services included here
 * 
 * @author Osanda Wedamulla
 *
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

	public static String FB_URL = "https://graph.facebook.com/v3.2/me?fields=id,first_name,last_name,name,email,gender";

	public static String BASE_FB_URL = "https://graph.facebook.com/v3.2/";

	private final RestTemplate restTemplate;

	private final UserRepository userRepository;
	private final PhotoRepository photoRepository;
	private final AlbumRepository albumRepository;

	public Object getUserAndPhotoDetails(Payload payload) throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		// facebook user information starts here
		String url = FB_URL + "&access_token=" + payload.getAccess_token();

		ResponseEntity<FacebookResponse> userResponse = null;

		try {
			userResponse = restTemplate.exchange(url, HttpMethod.GET, entity, FacebookResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacebookException();
		}
		log.info("Getting user data complete.");

		// facebook user photo details available here
		FacebookResponse userData = null;

		if (userResponse != null && userResponse.getStatusCode() == HttpStatus.OK) {
			userData = userResponse.getBody();
		}

		String userId = userData.getId();

		String phot_url = BASE_FB_URL + userId + "/photos?fields=id,name,webp_images,link,album&access_token="
				+ payload.getAccess_token();

		ResponseEntity<Photos> photoResponse = null;

		try {
			photoResponse = restTemplate.exchange(phot_url, HttpMethod.GET, entity, Photos.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacebookException();
		}
		log.info("Getting photo details and links complete.");

		Photos picList = null;

		if (photoResponse != null && photoResponse.getStatusCode() == HttpStatus.OK) {
			picList = photoResponse.getBody();
		}

		Optional<User> userOpt = this.userRepository.findById(Long.parseLong(userData.getId()));

		if (userOpt.isPresent()) {
			log.info("User already exits.");
			return ReponseMessage.createMessage("User already exits");
		}

		User user = new User();

		user.setFbid(Long.parseLong(userData.getId()));
		user.setName(userData.getName());
		user.setFirstName(userData.getFirst_name());
		user.setLastName(userData.getLast_name());
		user.setEmail(userData.getEmail());
		user.setGender(Gender.getGender(userData.getGender()));
		String profileUrl = "https://graph.facebook.com/v3.2/" + user.getFbid() + "/picture";
		user.setProfilePicUrl(profileUrl);

		try {
			this.userRepository.save(user);
			log.info("User details save for fbid : " + user.getFbid());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			throw new UserAlreadyExsitsException();
		}

		File profile = new File(DirectoryUtil.PROFILE_PIC, user.getFbid().toString());

		if (!profile.exists()) {
			profile.mkdirs();
		}

		File pic = new File(profile, user.getName() + "_" + user.getFbid() + ".jpeg");

		InputStream is = null;
		try {

			URL profilePicUrl = new URL(user.getProfilePicUrl());
			is = profilePicUrl.openStream();
			Files.copy(is, pic.toPath());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			is.close();
		}

		List<Photo> photoList = new ArrayList<>();
		Map<String, Album> albumMap = new HashMap<>();

		List<PictureData> picData = picList.getData();

		for (PictureData p : picData) {

			System.err.println("Data " + p.toString());

			for (ImageDto i : p.getWebp_images()) {

				Photo photo = new Photo();

				photo.setPhotoId(Long.parseLong(p.getId()));
				photo.setName(p.getName() == null ? "unnamed _" + Math.random() : p.getName());
				photo.setLink(p.getLink());

				File userPic = new File(DirectoryUtil.PHOTOS, user.getFbid() + "/" + photo.getName());

				if (!userPic.exists()) {
					userPic.mkdirs();
				}

				File savePic = new File(userPic, i.getWidth() + "x" + i.getHeight() + ".webp");
				InputStream inputStream = null;

				try {

					URL savePhotoUrl = new URL(i.getSource());
					inputStream = savePhotoUrl.openStream();
					Files.copy(inputStream, savePic.toPath());

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					inputStream.close();
				}

				photo.setUrl(i.getSource());
				photo.setWidth(i.getWidth());
				photo.setHeight(i.getHeight());

				photo.setFileName(savePic.getName());
				photo.setFullFilePath(savePic.getAbsolutePath());
				photo.setReletiveFilePath(savePic.getPath());
				photo.setFileSize(savePic.length());

				if (p.getAlbum() != null) {
					
					Album album = albumMap.get(p.getAlbum().getName());

					if (album == null) {
						album = new Album();

						album.setId(Long.parseLong(p.getAlbum().getId()));
						album.setName(p.getAlbum().getName());
						LocalDateTime parse = LocalDateTime.parse(p.getAlbum().getCreated_time(),
								DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
						album.setCreatedTime(parse);
						albumMap.put(p.getAlbum().getName(), album);
					}
					
					photo.setAlbum(album);
				}

				photoList.add(photo);

			} // image source loop

		} // photo details loop

		try {

			log.info("Saving albums : " + albumMap.size());
			this.albumRepository.saveAll(albumMap.values());
			log.info("Albums saved.");

			log.info("Saving photos : " + photoList.size());
			this.photoRepository.saveAll(photoList);
			log.info("Photos saved.");
			
			user.setPhotos(photoList);
			
			this.userRepository.save(user);
			log.info("User updated with photo list.");

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}

		return ReponseMessage.createMessage("Sucess");

	}// getUserAndPhotoDetails()

}