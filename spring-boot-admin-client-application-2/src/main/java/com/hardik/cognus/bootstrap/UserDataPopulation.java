package com.hardik.cognus.bootstrap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.hardik.cognus.entity.User;
import com.hardik.cognus.repository.UserRepository;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Configuration
@AllArgsConstructor
public class UserDataPopulation {

	private final UserRepository userRepository;

	@PostConstruct
	public void userDataPopulation() {
		final var userList = new ArrayList<User>();
		final var random = new Random();

		for (int i = 1; i < 300; i++) {
			final var user = new User();
			user.setDateOfBirth(LocalDate.of(random.ints(1, 1950, 2001).sum(), random.ints(1, 1, 13).sum(),
					random.ints(1, 1, 26).sum()));
			user.setFullName(RandomString.make(7));
			user.setEmailId(user.getFullName() + "@gmail.com");
			userList.add(user);
		}

		userRepository.saveAll(userList);
	}

}
