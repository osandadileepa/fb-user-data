package com.osanda.roihunter.fbuserdata.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osanda.roihunter.fbuserdata.exception.FacebookException;
import com.osanda.roihunter.fbuserdata.exception.UserAlreadyExsitsException;
import com.osanda.roihunter.fbuserdata.exception.UserNotFoundException;
import com.osanda.roihunter.fbuserdata.model.dto.Payload;
import com.osanda.roihunter.fbuserdata.model.dto.response.ResponseData;
import com.osanda.roihunter.fbuserdata.service.UserService;
import com.osanda.roihunter.fbuserdata.util.ReponseMessage;

import lombok.RequiredArgsConstructor;

/***
 * user related rest contollers
 * 
 * @author Osanda Wedamulla
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${spring.data.rest.base-path}/users/")
public class UserController {

	private final UserService userService;

	/***
	 * @author Osanda Wedamulla
	 * 
	 * @param payload
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<?> getUserAndPhotos(@RequestBody Payload payload) {

		ResponseData rs = new ResponseData();

		try {
			return ResponseEntity.ok(this.userService.getUserAndPhotoDetails(payload));
		} catch (FacebookException e) {
			rs.setMessage("Invalid Access Token");
			rs.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			rs.setTime_stamp(LocalDateTime.now());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReponseMessage.error(rs));
		} catch (UserAlreadyExsitsException e) {
			rs.setMessage("Error saving new user details or photo details.");
			rs.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			rs.setTime_stamp(LocalDateTime.now());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReponseMessage.error(rs));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}// getUserAndPhotos()

	/**
	 * @author Osanda Wedamulla
	 * 
	 * @param userId
	 * @return
	 */
	@DeleteMapping(value = "{user_fb_id}")
	public ResponseEntity<?> deleteUserDetailsAndPhotos(@PathVariable("user_fb_id") String userId) {

		ResponseData rs = new ResponseData();

		try {
			return ResponseEntity.ok(this.userService.deleteUserData(userId));
		} catch (Exception e) {
			rs.setMessage("User deletion unsccessfull error occured.");
			rs.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			rs.setTime_stamp(LocalDateTime.now());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReponseMessage.error(rs));
		}

	}// deleteUserDetailsAndPhotos()

	/***
	 * @author Osanda Wedamulla
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "{user_fb_id}")
	public ResponseEntity<?> getUserDetails(@PathVariable("user_fb_id") String userId) {

		ResponseData rs = new ResponseData();

		try {
			return ResponseEntity.ok(this.userService.getUserDetailsFromId(userId));
			
		} catch (UserNotFoundException e) {
			rs.setMessage("Requested user details not available.");
			rs.setStatus_code(HttpStatus.BAD_GATEWAY.value());
			rs.setTime_stamp(LocalDateTime.now());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReponseMessage.error(rs));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}// getUserDetails()

	/***
	 * @author Osanda Wedamulla
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "{user_fb_id}/photos")
	public ResponseEntity<?> getUserDetailsWithPhotos(@PathVariable("user_fb_id") String userId) {
		
		ResponseData rs = new ResponseData();
		
		try {
			
			return ResponseEntity.ok(this.userService.getUserPhotoDetails(userId));
			
		} catch (UserNotFoundException e) {
			rs.setMessage("Requested user details not available.");
			rs.setStatus_code(HttpStatus.BAD_GATEWAY.value());
			rs.setTime_stamp(LocalDateTime.now());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReponseMessage.error(rs));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}// getUserDetailsWithPhotos()

}// UserController{}