package com.lzheng.coolpan.Service;

import com.lzheng.coolpan.Error.AccountError;
import com.lzheng.coolpan.Error.Registered;
import com.lzheng.coolpan.Error.notActive;
import com.lzheng.coolpan.dao.AccountDao;
import com.lzheng.coolpan.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Value("${account.maxsize}")
    private int maxsize;


    @Autowired
    private MailService mailService;

    public Account selectByName(String name) {
        return accountDao.selectByName(name);
    }

    /***
     * @author lzheng
     * @date 2019/12/16
     * @return
     * @Description 注册
     **/
    public void Firstsign(String name, String password, String mail) throws AccountError {

        Account account = accountDao.selectByName(name);
        if (account != null) {//已经注册过
            if (account.getStatus() == 1) {
                throw new Registered();
            } else if (account.getStatus() == 0){
                throw new notActive();
            }

        }
        account = new Account();
        account.setName(name);
        account.setPassword(password);
        account.setEmial(mail);
        account.setStatus(0);
        int code = (int) (new Date().getTime() % 1000000);
        account.setNowsize(code);//这里先给一串随机数字,用来验证用户是否激活
        account.setMaxsize(maxsize);
        String ename = account.getName();
        sendMail(mail, code, ename);
        accountDao.insert(account);

    }

    public void sendMail(String mail, int code, String ename) {
        new Thread(){
            @Override
            public void run() {
                try {
                    mailService.sendSimpleMail(mail, code, ename);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public boolean verifyCode(String name, int code) {
        Account account = accountDao.selectByName(name);
        if (account != null && account.getNowsize() == code) {
            account.setNowsize(0);
            account.setStatus(1);
            account.setSavefilename(account.getName() + code);
            accountDao.updateByPrimaryKey(account);
            File file = new File(SavePath + account.getSavefilename());
            if (!file.exists()) {//如果文件夹不存在
                file.mkdir();//创建文件夹
            }
            return true;
        } else {
            return false;
        }
}

    public Map<String,String> Validated(Account account,String password){
        Map<String,String> map=new HashMap<>();
        if (account.getName().length()<6||account.getName().length()>18){
            map.put("id","用户名长度必须在6-18之间");
        }else if (account.getPassword().length()<6||account.getPassword().length()>30){
            map.put("password","密码长度必须在6-30之间");
        }else if (!account.getPassword().equals(password)){
            map.put("password2","两次输入的密码不同");
        }
        return map;
    }


    public void upDate(Account account){
        accountDao.updateByPrimaryKey(account);
    }

}
