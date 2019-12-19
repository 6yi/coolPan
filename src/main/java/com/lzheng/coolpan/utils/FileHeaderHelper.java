package com.lzheng.coolpan.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName FileHeaderHelper
 * @Author 刘正
 * @Date 2019/12/18 13:42
 * @Version 1.0
 * @Description:
 */



@Component
@ConfigurationProperties(prefix = "file")
public class FileHeaderHelper {

//    @Value("${file.maps}")
    private  HashMap<String,Integer> maps;

    public HashMap<String, Integer> getMaps() {
        return maps;
    }

    public void setMaps(HashMap<String, Integer> maps) {
        this.maps = maps;
    }

    public  int getFileType(String filetype){
        System.out.println(filetype);
        Integer type=maps.get(filetype);
        if (type==null){
            return 4;
        }else{
            System.out.println("type="+type);
            return type;
        }
        }



}
