/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.start;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darko.aleksovski
 */
@Startup
@Singleton
public class AppProperties {

    public static boolean MAIL_SMTP_AUTH;
    public static boolean MAIL_SMTP_STARTTLS_ENABLE;
    public static String MAIL_SMTP_HOST;
    public static String MAIL_SMTP_PORT;
    public static String MAIL_USER;
    public static String MAIL_PASSWORD;
    public static String MAIL_NAME;
    public static String OBRAZEC_TAGS_ID;
    public static String FILE_PATH;
    public static String TEMP_FILE_PATH;
    public static String FILE_SYSTEM_PATH;
    public static String RESUMABLE_TEMP;
    public static String RESUMABLE_FINISHED;
    public static String RESUMABLE_UNZIPPED;
    public static String OS_TYPE;
    public static String JDBC;
    public static String JDBC_USER;
    public static String JDBC_PASS;
    public static String RESENIJA_LOCATION;
    public static String KEYSTORE;
    public static String KEYSTORE_PASS;
    public static String CERT;
    public static String CERT_PASS;
    public static String TEMP_ZIP_PATH;
    public static String DIREKTOR;

    @PostConstruct
    private void loadProperties() {
        try {
            Properties prop = new Properties();
            InputStream propsStream = getClass().getClassLoader().getResourceAsStream("ApplicationSettings.properties");
            prop.load(propsStream);

            MAIL_SMTP_AUTH = Boolean.parseBoolean(prop.getProperty("mail.smtp.auth"));
            MAIL_SMTP_STARTTLS_ENABLE = Boolean.parseBoolean(prop.getProperty("mail.smtp.starttls.enable"));
            MAIL_SMTP_HOST = prop.getProperty("mail.smtp.host");
            MAIL_SMTP_PORT = prop.getProperty("mail.smtp.port");
            MAIL_USER = prop.getProperty("mail.user");
            MAIL_PASSWORD = prop.getProperty("mail.password");
            MAIL_NAME = prop.getProperty("mail.name");
            OBRAZEC_TAGS_ID = prop.getProperty("obrazec.tags.id");
            FILE_PATH = prop.getProperty("malmedFileLocation");
            TEMP_FILE_PATH = prop.getProperty("tempFileLocation");
            FILE_SYSTEM_PATH = prop.getProperty("filesystempath");
            RESUMABLE_TEMP = prop.getProperty("resumable.temp");
            RESUMABLE_FINISHED = prop.getProperty("resumable.finished");
            RESUMABLE_UNZIPPED = prop.getProperty("resumable.unzipped");
            OS_TYPE = prop.getProperty("osType");
            JDBC = prop.getProperty("jdbc");
            JDBC_USER = prop.getProperty("jdbc.user");
            JDBC_PASS = prop.getProperty("jdbc.pass");
            KEYSTORE = prop.getProperty("keystore");
            KEYSTORE_PASS = prop.getProperty("keystore_password");
            CERT = prop.getProperty("certificate");
            CERT_PASS = prop.getProperty("certificate_password");

        } catch (IOException ex) {
            Logger.getLogger(AppProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
