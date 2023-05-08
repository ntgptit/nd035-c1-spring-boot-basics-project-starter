package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HashService {

	public static final Logger LOGGER = LoggerFactory.getLogger(HashService.class);
	public static final String SECRET_KEY_FACTORY = "PBKDF2WithHmacSHA256";

	public String getHashedValue(String data, String salt) {

		byte[] hashedValue = null;

		int iterCount = 12288;
		int derivedKeyLength = 256;
		KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), iterCount, derivedKeyLength * 8);

		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY);
			hashedValue = factory.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
		}

		return Base64.getEncoder().encodeToString(hashedValue);
	}

}
