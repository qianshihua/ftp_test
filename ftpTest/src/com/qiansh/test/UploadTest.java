package com.qiansh.test;

import java.util.HashMap;
import java.util.Map;

import com.qiansh.SFTPConstants;
import com.qiansh.SFTPUtils;

public class UploadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> sftpDetails = new HashMap<String, String>();
		//����
//		String uploadPath = "INCOMING/uniapp/emall";
//	    sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "130.38.27.31");
//        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "gdweb");
//        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "newweb_1125");
//        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
        //����
		//select * from sys_config t where t.cfg_type='MALL' and CFG_KEY='PHOTO_FILE_UPLOAD_PATH';
		//ԭ����ֵ��INCOMING/uniapp/emall
        String uploadPath = "domains/gxImg/picTest";
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "134.128.55.12");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "yyt_pic");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "change_it@2014");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
        
        boolean sftpResult = new SFTPUtils().upload(uploadPath, "/Users/qianshihua/Desktop/��Ƭ/ѡƬ/tmp/_DSC2207.jpg", sftpDetails);
        System.err.println(sftpResult);
        /*�����ļ��Ĵ���
        String resultPathFileName= iImageService.upload2Sftp(realPath+"/"+fileName)+"/"+fileName;
		
		String resultFileName=resultPathFileName.substring(resultPathFileName.lastIndexOf("/")+1);
		if(resultFileName.length()<2){
			resultFileName=fileName;
		}
		String resultPath=resultPathFileName.substring(0,resultPathFileName.lastIndexOf("/"));
		//����file_upload_info���ҷ���file_id
		String fileId="U"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"99";
		FileUploadInfo fileUploadInfo = new FileUploadInfo();
		fileUploadInfo.setFileId(fileId);
		fileUploadInfo.setFileExt("jpg");
		fileUploadInfo.setFileName(resultFileName);
		fileUploadInfo.setFilePath(resultPath);
		fileUploadInfo.setFileServer("VFS_TS_FILE_SERVER");
		fileUploadInfo.setFileSize(fileSize);
		fileUploadInfo.setUserId(1);
		fileUploadInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		iImageService.insertFileUploadInfo(fileUploadInfo);
		*/
        
	}

}
