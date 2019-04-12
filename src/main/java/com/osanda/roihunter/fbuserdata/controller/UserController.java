package com.osanda.roihunter.fbuserdata.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osanda.roihunter.fbuserdata.exception.FacebookException;
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

	@PostMapping(value = "")
	public ResponseEntity<?> getUserAndPhotos(@RequestBody Payload payload) {

		try {
			return ResponseEntity.ok(this.userService.getUserAndPhotoDetails(payload));
		} catch (FacebookException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReponseMessage.error("Access Token invalid"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
