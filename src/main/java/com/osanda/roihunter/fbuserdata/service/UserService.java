package com.osanda.roihunter.fbuserdata.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.osanda.roihunter.fbuserdata.exception.FacebookException;
import com.osanda.roihunter.fbuserdata.model.dto.FacebookResponse;
import com.osanda.roihunter.fbuserdata.model.dto.Payload;
import com.osanda.roihunter.fbuserdata.model.dto.Photos;
import com.osanda.roihunter.fbuserdata.repository.AlbumRepository;
import com.osanda.roihunter.fbuserdata.repository.PhotoRepository;
import com.osanda.roihunter.fbuserdata.repository.UserRepository;
import com.osanda.roihunter.fbuserdata.util.ReponseMessage;

import lombok.RequiredArgsConstructor;

/***
 * user related services included here
 * 
 * @author Osanda Wedamulla
 *
 */

@Service
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

		// facebook user photo details available here
		FacebookResponse userData = null;

		if (userResponse != null && userResponse.getStatusCode() == HttpStatus.OK) {
			userData = userResponse.getBody();
		}

		System.err.println("User Data" + userData.toString());

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

		Photos picList = null;

		if (photoResponse != null && photoResponse.getStatusCode() == HttpStatus.OK) {

			picList = photoResponse.getBody();
		}

		System.err.println("Photo data" + picList.toString());

		return ReponseMessage.createMessage("Sucess");

	}// getUserAndPhotoDetails()

}