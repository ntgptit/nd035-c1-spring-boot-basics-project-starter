package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.User;

public interface UserServices {

	int addUser(User user);

	User findUserByUsername(String username);

	boolean isUsernameExisted(String username);
}
