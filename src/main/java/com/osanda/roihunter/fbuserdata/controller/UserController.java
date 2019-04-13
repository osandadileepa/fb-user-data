package com.osanda.roihunter.fbuserdata.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osanda.roihunter.fbuserdata.exception.FacebookException;
import com.osanda.roihunter.fbuserdata.exception.UserAlreadyExsitsException;
import com.osanda.roihunter.fbuserdata.model.dto.Payload;
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

	@PostMapping()
	public ResponseEntity<?> getUserAndPhotos(@RequestBody Payload payload) {

		try {
			return ResponseEntity.ok(this.userService.getUserAndPhotoDetails(payload));
		} catch (FacebookException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ReponseMessage.error("Access Token invalid"));
		} catch (UserAlreadyExsitsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ReponseMessage.error("Cannot add this user : Already Exsits."));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@DeleteMapping(value = "{id}")
	public void deleteUserDetailsAndPhotos(@PathVariable("id") String userId) {
		
		
	}
	
	
}
