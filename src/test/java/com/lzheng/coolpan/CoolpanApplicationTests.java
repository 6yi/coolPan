package com.lzheng.coolpan;

import com.lzheng.coolpan.dao.FilesDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class CoolpanApplicationTests {

    @Autowired
    private FilesDao dao;
    @Test
    void contextLoads() {
        dao.findByAccountId(2).forEach(System.out::println);
    }

}
