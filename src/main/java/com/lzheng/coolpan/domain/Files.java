package com.lzheng.coolpan.domain;

import java.io.Serializable;

/**
 * files
 * @author 
 */
public class Files implements Serializable {
    private Integer id;

    private Integer accountId;

    private String filename;

    private String filepath;

    private String filetype;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", filetype='" + filetype + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
}