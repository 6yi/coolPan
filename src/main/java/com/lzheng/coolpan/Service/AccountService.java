package com.lzheng.coolpan.Service;

import com.lzheng.coolpan.Error.AccountError;
import com.lzheng.coolpan.Error.Registered;
import com.lzheng.coolpan.Error.notActive;
import com.lzheng.coolpan.dao.AccountDao;
import com.lzheng.coolpan.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName AccountService
 * @Author 刘正
 * @Date 2019/12/16 15:50
 * @Version 1.0
 * @Description: 用户服务
 */

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    @Value("${file.SavePath}")
    private String SavePath;

    @Autowired
    private MailService mailService;

    public Account selectByName(String name){
        return accountDao.selectByName(name);
    }
    /***
     * @author lzheng
     * @date 2019/12/16
     * @return
     * @Description 注册
     **/
    public void Firstsign(String name,String password,String mail) throws AccountError {

        Account account=accountDao.selectByName(name);
        if(account!=null){//已经注册过
            if (account.getStatus()==1){
                throw new Registered();
            }else{
                throw new notActive();
            }

        }
        account=new Account();
        account.setName(name);
        account.setPassword(password);
        account.setEmial(mail);
        account.setStatus(0);
        int code=(int) (new Date().getTime()%1000000);
        account.setNowsize(code);//这里先给一串随机数字,用来验证用户是否激活
        account.setMaxsize(50000);
        String ename=account.getName();
        //开启线程发生邮件
        try {
            new Thread(){
                @Override
                public void run() {
                    try {
                        mailService.sendSimpleMail(mail,code,ename);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            accountDao.insert(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyCode(String name,int code){
            Account account=accountDao.selectByName(name);
            if (account!=null&&account.getNowsize()==code){
                account.setNowsize(0);
                account.setStatus(1);
                account.setSavefilename(account.getName()+code);
                accountDao.updateByPrimaryKey(account);
                File file=new File(SavePath+account.getSavefilename());
                if(!file.exists()){//如果文件夹不存在
                    file.mkdir();//创建文件夹
                }
                return true;
            }else{
                return false;
            }
    }


}
