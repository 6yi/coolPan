package com.lzheng.coolpan.Service;

import com.lzheng.coolpan.dao.FilesDao;
import com.lzheng.coolpan.domain.Account;
import com.lzheng.coolpan.domain.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
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

    public List<Files> findFilesByType(Integer id,Integer type){
        return dao.findByType(id,type);
    }
    public void insert(Files files){
        dao.insertSelective(files);
    }

    public void delete(Integer id,String accountPath,String filename){
        dao.deleteByPrimaryKey(id);
        File file=new File(accountPath+"/"+filename);
        if(file.exists()&&file.isFile())
            file.delete();
    }

}
