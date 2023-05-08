package com.udacity.jwdnd.course1.cloudstorage.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.models.File;

@Mapper
public interface FileRepository {

	@Select("SELECT FILEID AS \"fileId\" , FILENAME AS \"fileName\" , CONTENTTYPE AS \"contentType\" , FILESIZE AS \"fileSize\" , USERID AS \"userId\" , FILEDATA AS \"fileData\" FROM FILES;")
	List<File> getAllFiles();

	@Select("SELECT FILEID AS \"fileId\" , FILENAME AS \"fileName\" , CONTENTTYPE AS \"contentType\" , FILESIZE AS \"fileSize\" , USERID AS \"userId\" , FILEDATA AS \"fileData\" FROM FILES WHERE FILEID = #{id}")
	File getFileById(@Param("id") Integer id);

	@Select("SELECT EXISTS ( SELECT 1 FROM FILES WHERE FILENAME = #{fileName} )")
	boolean isExistedFile(@Param("fileName") String fileName);

	@Insert("INSERT INTO FILES (FILENAME , CONTENTTYPE , FILESIZE , USERID , FILEDATA) VALUES(#{file.fileName} , #{file.contentType} , #{file.fileSize} , #{file.userId} , #{file.fileData})")
	int addFile(@Param("file") File file);

	@Delete("DELETE FROM FILES WHERE FILEID=#{fileId}")
	int removefile(@Param("fileId") Integer fileId);
}
