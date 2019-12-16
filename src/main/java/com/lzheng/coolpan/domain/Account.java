package com.lzheng.coolpan.domain;

import java.io.Serializable;
import java.util.List;

/**
 * account
 * @author 
 */
public class Account implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private String savefilename;

    private Integer maxsize;

    private Integer nowsize;

    public List<Files> getFilesList() {
        return FilesList;
    }

    public void setFilesList(List<Files> filesList) {
        FilesList = filesList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private List<Files> FilesList;


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSavefilename() {
        return savefilename;
    }

    public void setSavefilename(String savefilename) {
        this.savefilename = savefilename;
    }

    public Integer getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(Integer maxsize) {
        this.maxsize = maxsize;
    }

    public Integer getNowsize() {
        return nowsize;
    }

    public void setNowsize(Integer nowsize) {
        this.nowsize = nowsize;
    }
}