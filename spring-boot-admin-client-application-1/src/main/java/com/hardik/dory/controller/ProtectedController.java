package com.hardik.dory.controller;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joke")
@CrossOrigin(allowedHeaders = "*", origins = "*", value = "*")
public class ProtectedController {

	@GetMapping
	public ResponseEntity<?> getSecret() throws JSONException {
		final var response = new JSONObject();

		response.put("status", HttpStatus.OK.value());
		response.put("joke", "life");

		return ResponseEntity.ok(response.toString());

	}

}
