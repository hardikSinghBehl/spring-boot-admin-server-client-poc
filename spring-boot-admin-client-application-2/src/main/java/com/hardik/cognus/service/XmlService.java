package com.hardik.cognus.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hardik.cognus.dto.UserXmlDto;
import com.hardik.cognus.dto.UserXmlWrapperDto;
import com.hardik.cognus.entity.User;
import com.hardik.cognus.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class XmlService {

	private final UserRepository userRepository;

	public ByteArrayInputStream convertToXml() {
		final var userXmlWrapperDto = new UserXmlWrapperDto();
		final var userXmlDtos = new ArrayList<UserXmlDto>();
		final var outputStream = new ByteArrayOutputStream();

		userRepository.findAll().stream().forEach(user -> {
			final var userXmlDto = new UserXmlDto();
			userXmlDto.setDateOfBirth(user.getDateOfBirth());
			userXmlDto.setEmailId(user.getEmailId());
			userXmlDto.setName(user.getFullName());
			userXmlDto.setUserId(user.getId().toString());
			userXmlDtos.add(userXmlDto);
		});

		userXmlWrapperDto.setUsers(userXmlDtos);
		JAXB.marshal(userXmlWrapperDto, outputStream);
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	public ResponseEntity<?> readData(final MultipartFile file) {
		final var response = new JSONObject();
		final var userList = new ArrayList<User>();

		UserXmlWrapperDto userXmlWrapperDto = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UserXmlWrapperDto.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			userXmlWrapperDto = (UserXmlWrapperDto) jaxbUnmarshaller.unmarshal(file.getInputStream());
		} catch (JAXBException | IOException e) {
			throw new RuntimeException("Unable to proccess XML");
		}

		userXmlWrapperDto.getUsers().forEach(userXmlDto -> {
			final var user = new User();
			user.setId(UUID.fromString(userXmlDto.getUserId()));
			user.setEmailId(userXmlDto.getEmailId());
			user.setFullName(userXmlDto.getName());
			user.setDateOfBirth(userXmlDto.getDateOfBirth());
			userList.add(user);
		});

		userRepository.saveAll(userList);

		response.put("message", userList.size() + " users addes to the system");
		return ResponseEntity.ok(response.toString());
	}

}
