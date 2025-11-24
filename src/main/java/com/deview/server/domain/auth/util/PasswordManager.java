package com.deview.server.domain.auth.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordManager {

	private final PasswordValidation passwordValidation;

	public boolean isInvalid(String password) {
		return !passwordValidation.isValid(password);
	}
}