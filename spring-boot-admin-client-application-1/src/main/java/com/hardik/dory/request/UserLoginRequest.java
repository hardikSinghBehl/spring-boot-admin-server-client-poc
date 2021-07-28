package com.hardik.dory.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UserLoginRequest {

	private final String emailId;
	private final String password;

}
