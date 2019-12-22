package com.lzheng.coolpan.domain;

import java.io.Serializable;

/**
 * files
 * @author 
 */
public class Files implements Serializable {
    private Integer id;

    private Integer accountid;

    private String filename;

    private String filepath;

    private Integer filetype;

    private String size;

    private String time;

    private int ispublic;

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", accountid=" + accountid +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", filetype=" + filetype +
                ", size='" + size + '\'' +
                ", time='" + time + '\'' +
                ", ispublic=" + ispublic +
                '}';
    }
}