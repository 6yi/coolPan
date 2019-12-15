package com.lzheng.coolpan.Controller;

import com.lzheng.coolpan.Service.FileService;
import com.lzheng.coolpan.domain.Account;
import com.lzheng.coolpan.domain.Files;
import com.lzheng.coolpan.domain.retDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

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

    private static java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
    @Value("${file.SavePath}")
    private String SavePath;

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

    @RequestMapping("/files/download")
    public void Download(HttpServletResponse res, @RequestParam("filename") String fileName) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(SavePath + fileName )));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("export file finish");
    }

    @ResponseBody
    @PostMapping("/files/upload")
    public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(SavePath + fileName);
        try {
            file.transferTo(dest);
            Files newfile=new Files();
            newfile.setFilename(file.getName());
            newfile.setSize(String.format("%.2f",(file.getSize()/1024000.0))+"MB");
            Account account= (Account)request.getSession().getAttribute("account");
            newfile.setAccountid(account.getId());
            service.insert(newfile);
            return "上传成功";
        } catch (IOException e) {
                e.printStackTrace();
        }
        return "上传失败！";
    }

    @ResponseBody
    @RequestMapping("/files/type")
    public retDate findbyType(@RequestParam("type") Integer type,HttpServletRequest request){
        retDate date=new retDate();
        date.setCode("0");
        date.setMsg("");
        Object[] filesArry={};
        Account account= (Account)request.getSession().getAttribute("account");
        List<Files> files=null;
        if (type==6){
            files=service.findFilesById(account.getId());
        }else{
            files=service.findFilesByType(account.getId(),type);
        }


        if (files!=null){
           filesArry=files.toArray();
        }
        date.setData(filesArry);
        date.setCount(filesArry.length);
        return date;
    }


}
