package com.hardik.cognus.controller;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardik.cognus.entity.User;
import com.hardik.cognus.repository.UserRepository;
import com.hardik.cognus.service.XmlService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private final UserRepository userRepository;
	private final XmlService xmlService;

	@GetMapping(value = "/users/records", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Returns all users in system")
	public ResponseEntity<List<User>> retreiveUsersHandler() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping(value = "/users/xml", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Returns .xml file containing user records in system")
	public ResponseEntity<InputStreamResource> downloadXmlFileHandler() {
		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=user_records.xml")
				.body(new InputStreamResource(xmlService.convertToXml()));
	}

	@PostMapping(value = "/users/xml", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Reads data in .xml file and inserts into database")
	public ResponseEntity<?> readXmlFileHandler(
			@RequestParam(value = "file", required = true) final MultipartFile file) {
		return xmlService.readData(file);
	}
}
