package com.hardik.dory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.dory.request.UserCreationRequest;
import com.hardik.dory.request.UserLoginRequest;
import com.hardik.dory.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<?> userCreationHandler(
			@RequestBody(required = true) final UserCreationRequest userCreationRequest) {
		return userService.createAccount(userCreationRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<?> userLoginHandler(@RequestBody(required = true) final UserLoginRequest userLoginRequest) {
		return userService.login(userLoginRequest);
	}

}
