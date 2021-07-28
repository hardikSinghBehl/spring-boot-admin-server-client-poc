package com.hardik.dory.util;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hardik.dory.entity.User;
import com.hardik.dory.security.util.JwtUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class ResponseUtil {

	private final JwtUtils jwtUtils;

	public ResponseEntity<?> generateAccountCreationSuccessResponse() {
		final var response = new JSONObject();
		try {
			response.put("status", HttpStatus.OK.value());
			response.put("message", "You Can use Your Credentials To Login Now");
		} catch (JSONException exception) {
			log.error("Failed To Generate Valid JSON {}", exception);
		}
		return ResponseEntity.ok(response.toString());
	}

	public ResponseEntity<?> loginSuccessResponse(User user) {
		final var response = new JSONObject();
		try {
			response.put("status", HttpStatus.OK.value());
			response.put("jwt", jwtUtils.generateToken(user));
		} catch (JSONException exception) {
			log.error("Failed To Generate Valid JSON {}", exception);
		}
		return ResponseEntity.ok(response.toString());
	}

}
