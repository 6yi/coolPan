package com.lzheng.coolpan.Controller;

import com.lzheng.coolpan.Service.FileService;
import com.lzheng.coolpan.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FileCon
 * @Author 刘正
 * @Date 2019/12/14 16:23
 * @Version 1.0
 * @Description:
 */

@Controller
public class FileCon {
    @Autowired
    private FileService service;

    private Account account;

    @RequestMapping("/files")
    public String files(HttpServletRequest request){
        if (account==null){
            Account account=(Account)request.getSession().getAttribute("account");
            account.setFilesList(service.findFilesById(account.getId()));
            request.getSession().setAttribute("files",account.getFilesList());
        }
        return "files";
    }



}
