package com.lzheng.coolpan.Controller;
import com.lzheng.coolpan.Service.AccountService;
import com.lzheng.coolpan.Service.FileService;
import com.lzheng.coolpan.domain.Account;
import com.lzheng.coolpan.domain.Files;
import com.lzheng.coolpan.domain.retDate;
import com.lzheng.coolpan.utils.FileHeaderHelper;
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
import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    FileHeaderHelper fileHeaderHelper;

    @Value("${file.SavePath}")
    private String SavePath;

    @Autowired
    private AccountService accountService;


//    private Account account;


    @RequestMapping("/files")
    public String files(HttpServletRequest request){

        return "index";
    }

    @RequestMapping("/files/download")
    public void Download(HttpServletResponse res, @RequestParam("filepath") String filepath,@RequestParam("filename") String fileName) throws UnsupportedEncodingException {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(SavePath + filepath )));
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
        Account account= (Account)request.getSession().getAttribute("account");
        int nowsize=account.getNowsize();
        double filesize= (file.getSize()/1024000.0);
        if (file.isEmpty()||nowsize+filesize>account.getMaxsize()) {
            map.put("msg","error");
            map.put("code",0);
            return map;
        }
        String fileName = file.getOriginalFilename();
        String savedest=account.getSavefilename()+"/"+ UUID.randomUUID() +fileName;
        File dest = new File(SavePath +savedest);
        try {
            file.getInputStream();
            file.transferTo(dest);
            Files newfile=new Files();
            newfile.setFiletype(fileHeaderHelper.getFileType(file.getContentType()));
            newfile.setFilename(fileName);
            newfile.setFilepath(savedest);
            newfile.setTime(LocalDate.now().toString());
            newfile.setSize(String.format("%.2f",filesize)+"MB");
//            Account account= (Account)request.getSession().getAttribute("account");
            newfile.setAccountid(account.getId());
            service.insert(newfile);
            map.put("msg","ok");
            map.put("code",200);
            account.setNowsize((int)(nowsize+filesize));
            request.getSession().setAttribute("nowsize",(int)(nowsize+filesize));
            accountService.upDate(account);
            return map;
        } catch (IOException e) {
            map.put("msg","error");
            map.put("code",0);
            e.printStackTrace();
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
//        Account account= (Account)request.getSession().getAttribute("account");
        List<Files> files=null;
        Account account= (Account)request.getSession().getAttribute("account");
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

    @RequestMapping("/files/delete")
    public void delete(@RequestParam("filepath") String filepath,@RequestParam("id")Integer id,HttpServletRequest request){
            service.delete(id,filepath);
    }



}
