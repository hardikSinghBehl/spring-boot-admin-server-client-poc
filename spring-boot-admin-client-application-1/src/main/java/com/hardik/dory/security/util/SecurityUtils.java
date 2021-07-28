package com.hardik.dory.security.util;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityUtils {

	public User convert(com.hardik.dory.entity.User user) {
		return new User(user.getEmailId(), user.getPassword(), List.of());
	}

}
