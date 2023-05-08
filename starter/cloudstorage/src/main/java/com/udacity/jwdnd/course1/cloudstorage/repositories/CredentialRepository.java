package com.udacity.jwdnd.course1.cloudstorage.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;

@Mapper
public interface CredentialRepository {

	@Select("SELECT CREDENTIALS.CREDENTIALID AS \"credentialId\" , CREDENTIALS.URL AS \"url\" , CREDENTIALS.USERNAME AS \"username\" , CREDENTIALS.key AS \"key\" , CREDENTIALS.PASSWORD AS \"password\" , CREDENTIALS.USERID AS \"userId\" FROM CREDENTIALS INNER JOIN USERS ON CREDENTIALS.USERID = USERS.USERID WHERE USERS.USERID = #{userId}")
	List<Credential> getAllCredentials(@Param("userId") Integer userId);

	@Insert("INSERT INTO CREDENTIALS (URL , USERNAME , key , PASSWORD , USERID) VALUES(#{credential.url} , #{credential.username} , #{credential.key} , #{credential.password} , #{credential.userId})")
	int addCredential(@Param("credential") Credential credential);

	@Update("UPDATE CREDENTIALS SET URL=#{credential.url}, USERNAME=#{credential.username}, key=#{credential.key}, PASSWORD=#{credential.password} WHERE CREDENTIALID=#{credential.credentialId}")
	int updateCredential(@Param("credential") Credential credential);

	@Delete("DELETE FROM CREDENTIALS WHERE CREDENTIALID = #{id};")
	int removeCredential(@Param("id") Integer id);

}
