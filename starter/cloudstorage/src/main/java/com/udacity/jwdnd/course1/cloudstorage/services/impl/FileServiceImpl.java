package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.exception.CloudStorageException;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.repositories.FileRepository;
import com.udacity.jwdnd.course1.cloudstorage.services.FileServices;

@Service
public class FileServiceImpl implements FileServices {

	@Autowired
	private FileRepository fileRepository;

	@Override
	public int addFile(File file) {
		if (fileRepository.isExistedFile(file.getFileName())) {
			throw new CloudStorageException("This file already exists, please choose another file.");
		}
		return fileRepository.addFile(file);
	}

	@Override
	public List<File> getAllFiles() {
		return fileRepository.getAllFiles();
	}

	@Override
	public int removefile(Integer fileId) {
		return fileRepository.removefile(fileId);
	}

	@Override
	public File getFileById(Integer id) {
		return fileRepository.getFileById(id);
	}

}
