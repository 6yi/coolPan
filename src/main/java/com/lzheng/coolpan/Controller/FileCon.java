package com.lzheng.coolpan.Controller;

import com.lzheng.coolpan.Service.FileService;
import com.lzheng.coolpan.domain.Account;
import com.lzheng.coolpan.domain.Files;
import com.lzheng.coolpan.domain.retDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void Download(HttpServletResponse res, @RequestParam("filename") String fileName) throws UnsupportedEncodingException {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
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
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String,Object> map=new HashMap<>();
        if (file.isEmpty()) {
            map.put("msg","error");
            map.put("code",0);
            return map;
        }
        String fileName = file.getOriginalFilename();

        File dest = new File(SavePath + fileName);
        try {
            file.transferTo(dest);
            Files newfile=new Files();
            newfile.setFilename(fileName);
            newfile.setSize(String.format("%.2f",(file.getSize()/1024000.0))+"MB");
            Account account= (Account)request.getSession().getAttribute("account");
            newfile.setAccountid(account.getId());
            service.insert(newfile);
            map.put("msg","ok");
            map.put("code",200);
            return map;
        } catch (IOException e) {
            map.put("msg","error");
            map.put("code",0);

        }
        return map;
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
