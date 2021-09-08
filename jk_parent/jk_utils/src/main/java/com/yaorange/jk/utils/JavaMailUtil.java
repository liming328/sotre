package com.yaorange.jk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 *  编写Spring集成的javamail工具类
 * @author runmu
 * @version 1.0
 * @date 10:00 2019/4/27
 */
@Component
public class JavaMailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SimpleMailMessage mailMessage;

    //以下配置的是smtp服务器的设置
    /*private static final String USERNAME = "yaorange_mail@sina.com";
    private static final String PASSWORD = "yaorange201703";
    private static final String SMTP_HOST="smtp.sina.com";*/

    /**
     * spring发(外网)邮件
     * @param to
     * @param subject
     * @param text
     */
    public void sendMail(String to,String subject,String text){
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
    }

}











