package com.qiansh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

public class SFTPChannel
{
  Session session = null;
  Channel channel = null;

  private static final Logger LOG = Logger.getLogger(SFTPChannel.class.getName());

  public ChannelSftp getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException
  {
    String ftpHost = (String)sftpDetails.get("host");
    String port = (String)sftpDetails.get("port");
    String ftpUserName = (String)sftpDetails.get("username");
    String ftpPassword = (String)sftpDetails.get("password");

    int ftpPort = 22;
    if ((port != null) && (!(port.equals("")))) {
      ftpPort = Integer.valueOf(port).intValue();
    }

    JSch jsch = new JSch();
    this.session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
    LOG.debug("Session created.");
    if (ftpPassword != null) {
      this.session.setPassword(ftpPassword);
    }
    Properties config = new Properties();
    config.put("StrictHostKeyChecking", "no");
    this.session.setConfig(config);
    this.session.setTimeout(timeout);
    this.session.connect();
    LOG.debug("Session connected.");

    LOG.debug("Opening Channel.");
    this.channel = this.session.openChannel("sftp");
    this.channel.connect();
    LOG.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + 
      ", returning: " + this.channel);
    return ((ChannelSftp)this.channel);
  }

  public void closeChannel() throws Exception {
    if (this.channel != null) {
      this.channel.disconnect();
    }
    if (this.session != null)
      this.session.disconnect();
  }
}