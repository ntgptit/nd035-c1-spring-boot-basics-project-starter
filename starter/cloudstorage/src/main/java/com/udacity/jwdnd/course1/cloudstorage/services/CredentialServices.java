package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;

@Service
public interface CredentialServices {

	List<Credential> getAllCredentials(Integer userId);

	int addCredential(Credential credential);

	int updateCredential(Credential credential);

	int removeCredential(Integer id);

}
