package com.lzheng.coolpan.Controller;

import com.lzheng.coolpan.Error.AccountError;
import com.lzheng.coolpan.Service.AccountService;
import com.lzheng.coolpan.dao.AccountDao;
import com.lzheng.coolpan.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AccountCon
 * @Author 刘正
 * @Date 2019/12/14 15:13
 * @Version 1.0
 * @Description:  用户控制层
 */

@Controller
public class AccountCon {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/in")
    public void in(@RequestParam("name")String name,
                     @RequestParam("password")String password,
                     HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {
        Integer number = (Integer)request.getSession().getAttribute("number");
        if (number==null){
            number=1;
        }else{
            if (number>4){
                request.getSession().setAttribute("msg","失败次数过多");
                request.getRequestDispatcher("/login").forward(request,response);
            }
            number++;
        }
        request.getSession().setAttribute("number",number);
        Account user=accountService.selectByName(name);
        if(user==null||!user.getPassword().equals(password)){
            request.getSession().setAttribute("msg","密码错误");
            request.getRequestDispatcher("/login").forward(request,response);
        }

        request.getSession().setAttribute("needLogin","1");
        request.getSession().setAttribute("account",user);
        request.getRequestDispatcher("/files").forward(request,response);
    }

    @RequestMapping("/sign")
    public String signHTML(){
        return "signup";
    }

    /**
     * @author lzheng
     * @date 2019/12/16
     * @return
     * @Description 注册
     **/
    @RequestMapping("/sign/in")
    public String sign(@RequestParam("name")String name
                    ,@RequestParam("password")String password
                    ,@RequestParam("mail")String mail
                    ,HttpServletRequest request
                    ,HttpServletResponse response) throws ServletException, IOException {

        Integer signNumber = (Integer)request.getSession().getAttribute("signNumber");
        if (signNumber==null){
            signNumber=1;
        }else{
            if (signNumber>4){
                request.getSession().setAttribute("signmsg","注册次数过多");
                request.getRequestDispatcher("/sign").forward(request,response);
            }
            signNumber++;
        }
        try {
            accountService.Firstsign(name, password,mail);
        } catch (AccountError accountError) {
            request.getSession().setAttribute("signmsg",accountError.getMessage());
            request.getRequestDispatcher("/sign").forward(request,response);
        }
        return "loginsuccess";
    }

    /**
     * @author lzheng
     * @date 2019/12/16
     * @return
     * @Description  激活
     **/
    @RequestMapping("/sign/v")
    public String verify(@RequestParam("code") int code){

    }



}
