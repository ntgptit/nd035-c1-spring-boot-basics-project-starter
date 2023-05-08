package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.repositories.UserRepository;

@Service
public class AuthenticationService implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EncryptionService encryptionService;

	@Override
	public Authentication authenticate(Authentication authentication) {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = userRepository.findUserByUsername(username);

		if (user == null
				|| !StringUtils.equals(encryptionService.decryptValue(user.getPassword(), user.getSalt()), password)) {
			throw new BadCredentialsException("Incorrect username or password");

		}

		return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
