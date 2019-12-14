package com.lzheng.coolpan.Service;

import com.lzheng.coolpan.dao.FilesDao;
import com.lzheng.coolpan.domain.Account;
import com.lzheng.coolpan.domain.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName FileService
 * @Author 刘正
 * @Date 2019/12/14 16:22
 * @Version 1.0
 * @Description:
 */

@Component
public class FileService {

    @Autowired
    private FilesDao dao;

    public List<Files> findFilesById(Integer id){
        return dao.findByAccountId(id);
    }

}
