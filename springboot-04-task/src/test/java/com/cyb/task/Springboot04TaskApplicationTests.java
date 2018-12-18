package com.cyb.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TaskApplicationTests {

    //如果这里出现不能注入的情况，那么就是因为配置文件的关系  ，个人由于不小心的情况 将host 写成了post
    //检查了好久才检查出来
    @Autowired
    JavaMailSender mailSender;

    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");

        message.setTo("cybthread@163.com");
        message.setFrom("1332617055@qq.com");

        mailSender.send(message);
    }

    @Test
    public void testSendMessage(){
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //邮件设置
            helper.setSubject("通知！今晚开会");
            helper.setText("<b style='color:red'>今天7:30开会</b>",true);
            helper.setTo("cybthread@163.com");
            helper.setFrom("1332617055@qq.com");

            //上传文件
            helper.addAttachment("1.jps",new File("E:\\Temp\\1.jpg"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}

