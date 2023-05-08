package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class File {

	private Integer fileId;
	private String fileName;
	private String contentType;
	private Long fileSize;
	private Integer userId;
	private byte[] fileData;

}
