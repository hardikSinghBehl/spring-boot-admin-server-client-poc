package com.hardik.dory.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hardik.dory.entity.User;
import com.hardik.dory.repository.UserRepository;
import com.hardik.dory.request.UserCreationRequest;
import com.hardik.dory.request.UserLoginRequest;
import com.hardik.dory.util.ResponseUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final ResponseUtil responseUtil;

	public ResponseEntity<?> createAccount(final UserCreationRequest userCreationRequest) {
		final var user = new User();
		user.setEmailId(userCreationRequest.getEmailId());
		user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));

		userRepository.save(user);

		return responseUtil.generateAccountCreationSuccessResponse();
	}

	public ResponseEntity<?> login(UserLoginRequest userLoginRequest) {
		final var user = userRepository.findByEmailId(userLoginRequest.getEmailId())
				.orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
		if (passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
			return responseUtil.loginSuccessResponse(user);
		}
		throw new UsernameNotFoundException("Bad credentials");
	}

}
