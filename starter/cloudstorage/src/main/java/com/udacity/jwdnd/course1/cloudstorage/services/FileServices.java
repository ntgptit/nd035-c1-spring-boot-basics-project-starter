package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.models.File;

@Service
public interface FileServices {

	List<File> getAllFiles();

	int addFile(File file);

	int removefile(Integer fileId);

	File getFileById(Integer id);
}
