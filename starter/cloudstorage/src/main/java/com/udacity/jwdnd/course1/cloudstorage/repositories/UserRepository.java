package com.udacity.jwdnd.course1.cloudstorage.repositories;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.models.User;

@Mapper
public interface UserRepository {

	@Insert("INSERT INTO USERS (USERNAME, SALT, PASSWORD, FIRSTNAME, LASTNAME) VALUES(#{user.username}, #{user.salt}, #{user.password}, #{user.firstName}, #{user.lastName})")
	int addUser(@Param("user") User user);

	@Select("SELECT USERID, USERNAME, SALT, PASSWORD, FIRSTNAME, LASTNAME FROM USERS WHERE USERNAME=#{username}")
	User findUserByUsername(@Param("username") String username);

	@Select("SELECT EXISTS ( SELECT 1 FROM USERS WHERE USERNAME = #{username} )")
	boolean isUsernameExisted(@Param("username") String username);

}
