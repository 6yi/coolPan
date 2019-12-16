package com.lzheng.coolpan.Service;

import com.lzheng.coolpan.Error.AccountError;
import com.lzheng.coolpan.Error.Registered;
import com.lzheng.coolpan.Error.notActive;
import com.lzheng.coolpan.dao.AccountDao;
import com.lzheng.coolpan.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Account selectByName(String name){
        return accountDao.selectByName(name);
    }

    /***
     * @author lzheng
     * @date 2019/12/16
     * @return
     * @Description 注册
     **/
    public void sign(String name,String password) throws AccountError {
        Account account=accountDao.selectByName(name);
        if(account!=null){//已经注册过
            throw new Registered();

        }
        account.setName(name);
        account.setPassword(password);
        account.setNowsize((int) (new Date().getTime()%1000000));//这里先给一串随机数字,用来验证用户是否激活
        account.setMaxsize(50000);

    }


}
