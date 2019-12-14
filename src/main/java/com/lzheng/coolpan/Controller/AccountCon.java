package com.lzheng.coolpan.Controller;

import com.lzheng.coolpan.dao.AccountDao;
import com.lzheng.coolpan.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AccountCon
 * @Author 刘正
 * @Date 2019/12/14 15:13
 * @Version 1.0
 * @Description:
 */

@Controller
public class AccountCon {

    @Autowired
    private AccountDao accountDao;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/in")
    public String in(@RequestParam("name")String name,
                     @RequestParam("password")String password,HttpServletRequest request){
        Integer number = (Integer)request.getSession().getAttribute("number");
        if (number==null){
            number=1;
        }else{
            if (number>4){
                request.getSession().setAttribute("msg","失败次数过多");
                return "login";
            }
            number++;
        }
        request.getSession().setAttribute("number",number);
        Account user=accountDao.selectById(name);
        if(user==null||!user.getPassword().equals(password)){
            request.getSession().setAttribute("msg","密码错误");
            return "login";
        }

        request.getSession().setAttribute("needLogin","1");
        request.getSession().setAttribute("account",user);
        return "index";
    }


}
