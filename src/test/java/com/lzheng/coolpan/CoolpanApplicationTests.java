package com.lzheng.coolpan;

import com.lzheng.coolpan.Service.MailService;
import com.lzheng.coolpan.dao.AccountDao;
import com.lzheng.coolpan.dao.FilesDao;
import com.lzheng.coolpan.domain.Account;
import com.lzheng.coolpan.utils.FileHeaderHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
class CoolpanApplicationTests {

    @Autowired
    private MailService mailService;

    @Autowired
    private FilesDao dao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    FileHeaderHelper fileHeaderHelper;

    @Test
    void contextLoads() {
        dao.findByAccountId(2).forEach(System.out::println);
    }

    @Test
    void contextLoads2() {
        File file=new File("E:\\Save\\"+"ideaIU-2019.3.exe");
        if(file.exists()&&file.isFile())
            file.delete();
    }


    @Test
    void contextLoads3() {
        Account account=new Account();
        account.setName("1234");
        account.setPassword("lzheng");
        account.setMaxsize(50000);
        account.setNowsize(300);
        account.setSavefilename("lzheng12343");
        accountDao.insert(account);
    }

    @Test
    void contextLoads4() {
       System.out.println(new Date().getTime()%1000000);
    }

    @Test
    void contextLoads5() throws UnsupportedEncodingException {
//        mailService.sendSimpleMail("lzhengycy@outlook.com",43787);
//        System.out.println(fileHeaderHelper.getMaps().get("application/x-zip-compressed"));
        Set<String> strings = fileHeaderHelper.getMaps().keySet();
//        System.out.println(fileHeaderHelper.getMaps().get("image/jpeg"));
        for(String key:strings){
//            if (key.equals("image/jpeg"))
//                System.out.println("yes");
            System.out.println(key);
        }
//        System.out.println(LocalDate.now());
    }

}
