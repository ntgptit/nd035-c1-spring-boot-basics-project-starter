package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.repositories.CredentialRepository;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialServices;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

@Service
public class CredentialServiceImpl implements CredentialServices {

	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private CredentialRepository credentialRepository;

	@Override
	public List<Credential> getAllCredentials(Integer userId) {

		List<Credential> allCredentials = credentialRepository.getAllCredentials(userId);

		allCredentials.forEach(c -> c.setPasswordDecrypt(encryptionService.decryptValue(c.getPassword(), c.getKey())));

		return allCredentials;

	}

	@Override
	public int addCredential(Credential credential) {

		encryptPassword(credential);

		if (credential.getCredentialId() == null) {
			return credentialRepository.addCredential(credential);
		}

		return credentialRepository.updateCredential(credential);
	}

	@Override
	public int updateCredential(Credential credential) {
		encryptPassword(credential);
		return credentialRepository.updateCredential(credential);
	}

	@Override
	public int removeCredential(Integer id) {
		return credentialRepository.removeCredential(id);
	}

	private void encryptPassword(Credential credential) {

		String key = credential.getKey() == null ? "AES" + System.currentTimeMillis() : credential.getKey();

		credential.setKey(key);
		String password = credential.getPassword();

		credential.setPasswordDecrypt(password);
		credential.setPassword(encryptionService.encryptValue(password, key));
	}

}
