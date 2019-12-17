package com.lzheng.coolpan.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * account
 * @author 
 */
public class Account implements Serializable {
    private Integer id;

    @NotEmpty(message="请输入用户名!")
    @Size(min = 6, max = 30, message = "2<长度<30")
    private String name;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 30, message = "2<长度<30")
    private String password;

    private String savefilename;

    private Integer maxsize;

    private Integer nowsize;
    @Email(message = "邮件格式不对")
    private String emial;

    private Integer status;

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

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}