package com.lzheng.coolpan.Service;

import com.lzheng.coolpan.dao.FilesDao;
import com.lzheng.coolpan.domain.Account;
import com.lzheng.coolpan.domain.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.SavePath}")
    private  String SavePath;
    public List<Files> findFilesById(Integer id){
        return dao.findByAccountId(id);
    }
    public List<Files> findFilesByType(Integer id,Integer type){
        return dao.findByType(id,type);
    }
    public void insert(Files files){
        dao.insertSelective(files);
    }
    public void delete(Integer id,String filepath){
        dao.deleteByPrimaryKey(id);
        File file=new File(SavePath+filepath);
        if(file.exists()&&file.isFile())
            file.delete();
    }
    public List<Files> findPublicFilesByType(int type){
        return dao.findPublicFilesByType(type);
    }
    public List<Files> findPublicFiles(){
        return dao.findPublicFiles();
    }
    public List<Files> findPublicFilesById(Integer id){
        return dao.findPublicFilesById(id);
    }
    public void UpadateIspublicById(Integer id,int state){
        dao.UpadateIspublicById(id,state);
    }
}
