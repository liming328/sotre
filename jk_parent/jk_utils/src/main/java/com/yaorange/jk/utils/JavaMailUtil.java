package com.yaorange.jk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

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
    /*private static final String USERNAME = "lm595952757@sina.com";
    private static final String PASSWORD = "8e45814312ea0861";
    private static final String SMTP_HOST="smtp.sina.com";*/

    /**
     * spring发(外网)邮件 纯文本
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

    /**
     * 带图片带附件
     */
    public void ssendHtmlMail(String to,String subject,String content,String imagePath,String filePath,String saveAsFileName) throws MessagingException {
        //创建一封可携带图片，附件的邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //创建发送对象
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //通过发送对象设置邮件的细节
        helper.setFrom(mailMessage.getFrom());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true); //第二个参数true说明内容要解析为 html 代码
        //添加图片
        FileSystemResource resource = new FileSystemResource(new File(imagePath));
        helper.addInline("image",resource);
        //发送附件
        FileSystemResource zipResource = new FileSystemResource(new File(filePath));
        helper.addAttachment(saveAsFileName,zipResource);
        javaMailSender.send(mimeMessage);
    }

}











