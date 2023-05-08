package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.repositories.UserRepository;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public int addUser(User user) {
		return userRepository.addUser(encryptPassword(user));
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	private User encryptPassword(User user) {

		String salt = user.getSalt() == null ? "AES" + System.currentTimeMillis() : user.getSalt();

		user.setSalt(salt);

		user.setPassword(encryptionService.encryptValue(user.getPassword(), salt));

		return user;
	}

	@Override
	public boolean isUsernameExisted(String username) {
		return userRepository.isUsernameExisted(username);
	}

}
