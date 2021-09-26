package com.yaorange.jk.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 测试发送邮件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mail.xml")
public class SpringJavaMailTest {
   @Autowired
    private JavaMailSender javaMailSender;
    @Test
    public void test() throws MessagingException {
        //创建一封可携带图片，附件的邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //创建发送对象
        MimeMessageHelper  helper = new MimeMessageHelper(mimeMessage, true);

        //通过发送对象设置邮件的细节
        helper.setFrom("lm595952757@sina.com");
        helper.setTo("lm595952757@sina.com");
        helper.setSubject("这是来自百合网的一个请求");
        helper.setText("<html><head></head><body><h1>hello kg </h1> <img src='cid:image'/></body></html>",true); //第二个参数true说明内容要解析为 html 代码
        //添加图片
        FileSystemResource resource = new FileSystemResource(new File("C:\\Users\\lmyyds\\Desktop\\519a0e522b8112e67c5e27c0ba56c19.png"));
        helper.addInline("image",resource);
        //发送附件
        FileSystemResource fileSystemResource = new FileSystemResource(new File("E:\\JK.sql"));
        helper.addAttachment("JK.sql", fileSystemResource);
        javaMailSender.send(mimeMessage);
    }
}
