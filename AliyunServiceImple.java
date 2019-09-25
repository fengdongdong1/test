package com.sanping.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import common.ali.Oos;
import common.ali.OosEntity;
import common.response.BaseResponse;
import common.response.ErrInfo;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
@Service
public class AliyunServiceImple implements AliyunService {

	// 阿里云OOS上传文件
	@Override
	public BaseResponse aliyunUploadFile(OosEntity oosEntity, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		String fileName = multipartFile.getOriginalFilename();

		String newFileName = Oos.createFileName(fileName);

		String folder = "";
		if (oosEntity.getFolder() != null) {

			folder = oosEntity.getFolder();
		}

		String filePath = folder + newFileName;

		Oos.uploadFileWithBucketName(multipartFile, filePath);

		oosEntity.setFilePath(filePath);

		return new BaseResponse(ErrInfo.SUCCESS, oosEntity);
	}
	 
	public static void main(String[] args) {
		Oos.deleteFile("test\\1551776571929.jpg");
	}
}
