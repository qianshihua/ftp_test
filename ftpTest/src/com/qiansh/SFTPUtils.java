package com.qiansh;

import com.jcraft.jsch.ChannelSftp;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class SFTPUtils
{
  private Logger log = Logger.getLogger(super.getClass());

  private String directory = "/INCOMING/uniapp/emall";

  private static Map<String, String> sftpDetails = new HashMap();

  static {
    sftpDetails.put("host", "130.38.27.31");
    sftpDetails.put("username", "gdweb");
    sftpDetails.put("password", "newweb_1125");
    sftpDetails.put("port", "22");
  }

  public boolean upload(String uploadFile)
  {
    boolean result = false;
    try {
      SFTPChannel channel = new SFTPChannel();
      ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
      chSftp.cd(this.directory);
      File file = new File(uploadFile);

      chSftp.put(uploadFile, file.getName(), 0);
      chSftp.quit();
      channel.closeChannel();
      file = null;
      result = true;
    } catch (Exception e) {
      this.log.error(this, e);
    }
    return result;
  }

  public boolean upload(String directory, String uploadFile, Map<String, String> sftpDetails)
  {
    boolean result = false;
    try {
      SFTPChannel channel = new SFTPChannel();
      ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
      chSftp.cd(directory);
      File file = new File(uploadFile);
      chSftp.put(uploadFile, file.getName(), 0);
      chSftp.quit();
      channel.closeChannel();
      file = null;
      result = true;
    } catch (Exception e) {
      this.log.error(this, e);
    }
    return result;
  }

  public ByteArrayOutputStream download(String downloadFile)
  {
    ByteArrayOutputStream result = null;
    InputStream is = null;
    BufferedInputStream bis = null;
    try {
      SFTPChannel channel = new SFTPChannel();
      ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
      chSftp.cd(this.directory);
      is = chSftp.get(downloadFile);
      bis = new BufferedInputStream(is);
      result = new ByteArrayOutputStream();
      byte[] b = new byte[1024];
      int len = -1;
      while ((len = bis.read(b, 0, 1024)) != -1) {
        result.write(b, 0, len);
      }
      result.flush();
      chSftp.quit();
      channel.closeChannel();
    } catch (Exception e) {
      this.log.error(this, e);
    } finally {
      try {
        if (is != null) {
          is.close();
          is = null;
        }
      } catch (Exception e) {
        this.log.error(this, e);
      }
      try {
        if (bis != null) {
          bis.close();
          bis = null;
        }
      } catch (Exception e) {
        this.log.error(this, e);
      }
    }
    return result;
  }

  public ByteArrayOutputStream download(String directory, String downloadFile, Map<String, String> sftpDetails)
  {
    ByteArrayOutputStream result = null;
    if ((sftpDetails == null) || (sftpDetails.size() == 0))
      sftpDetails = SFTPUtils.sftpDetails;
    try
    {
      SFTPChannel channel = new SFTPChannel();
      ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
      chSftp.cd(directory);
      InputStream is = chSftp.get(downloadFile);
      BufferedInputStream bis = new BufferedInputStream(is);
      result = new ByteArrayOutputStream();
      byte[] b = new byte[1024];
      int len = -1;
      while ((len = bis.read(b, 0, 1024)) != -1) {
        result.write(b, 0, len);
      }
      result.flush();
      is.close();
      bis.close();
      chSftp.quit();
      channel.closeChannel();
    } catch (Exception e) {
      this.log.error(this, e);
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    SFTPUtils util = new SFTPUtils();
    Map sftpDetails = new HashMap();

    sftpDetails.put("host", "133.0.191.18");
    sftpDetails.put("username", "ftpimg01");
    sftpDetails.put("password", "ftpimg01_1232");
    sftpDetails.put("port", "22");

    String uploadFile = "U2014052315430110062.jpg";
    String directory = "/INCOMING/mall";

    util.download(directory, uploadFile, sftpDetails);
  }
}