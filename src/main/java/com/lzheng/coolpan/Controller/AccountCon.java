package com.lzheng.coolpan.Controller;

import com.lzheng.coolpan.Error.AccountError;
import com.lzheng.coolpan.Service.AccountService;
import com.lzheng.coolpan.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String login(HttpServletRequest request){
        if (request.getSession().getAttribute("account")!=null){
            return "index";
        }
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

        if(user!=null&&user.getStatus()==0){
            request.setAttribute("msg","该账户未激活,已重新发送激活邮件");
            accountService.sendMail(user.getEmial(),user.getNowsize(),user.getName());
            request.getRequestDispatcher("/login").forward(request,response);
        }else if(user==null||!user.getPassword().equals(password)){
            request.setAttribute("msg","密码错误");
            request.getRequestDispatcher("/login").forward(request,response);
        }else{
            request.getSession().setAttribute("needLogin","1");
            request.getSession().setAttribute("account",user);
            request.getSession().setAttribute("name",name);
            double bfb=((user.getNowsize()*1.0)/user.getMaxsize())*100;
            request.getSession().setAttribute("bfb",bfb);
            request.getSession().setAttribute("nowsize",user.getNowsize());
            request.getSession().setAttribute("maxsize",user.getMaxsize());
            request.getRequestDispatcher("/files").forward(request,response);
        }


    }

    @RequestMapping("/sign")
    public String signHTML(){
        return "signup";
    }

//    @ResponseBody
//    @PostMapping("/Validated")
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String,Object> Validated(@Validated Account account
//                                        ,@RequestParam("password2")String password2
//                                        ,BindingResult bindingResult
//                                        ,HttpServletRequest request
//                                        ,HttpServletResponse response){
//        Map<String,Object> map=new HashMap<>();
//        if (bindingResult.hasErrors()) {
//            List<ObjectError> errorList = bindingResult.getAllErrors();
//            List<String> mesList=new ArrayList<String>();
//            for (int i = 0; i < errorList.size(); i++) {
//                mesList.add(errorList.get(i).getDefaultMessage());
//                System.out.println(errorList.get(i).getDefaultMessage());
//            }
//            map.put("status", false);
//            map.put("error", mesList);
//        }else {
//            map.put("status", true);
//        }
//        return map;
//    }


    /**
     * @author lzheng
     * @date 2019/12/16
     * @return
     * @Description 注册
     **/
    @PostMapping("/sign/in")
    public String sign(@RequestParam("name")String name,
                       @RequestParam("password")String password
                    ,@RequestParam("password2")String password2
                    ,@RequestParam("email")String email
                    ,HttpServletRequest request
                    ,HttpServletResponse response) throws ServletException, IOException {

        Account account=new Account();
        account.setEmial(email);
        account.setPassword(password);
        account.setName(name);
        if(accountService.Validated(account,password2).size()>0){
            request.getSession().setAttribute("emap",accountService.Validated(account,password2));
            request.getRequestDispatcher("/sign").forward(request,response);
        }else {
            Integer signNumber = (Integer) request.getSession().getAttribute("signNumber");
            if (signNumber == null) {
                signNumber = 1;
            } else if (signNumber > 4) {
                request.getSession().setAttribute("signmsg", "注册次数过多");
                request.getRequestDispatcher("/sign").forward(request, response);
            }
            signNumber++;
            try {
                accountService.Firstsign(name, password, email);
            } catch (AccountError accountError) {
                request.getSession().setAttribute("signmsg", accountError.getMessage());
                request.getRequestDispatcher("/sign").forward(request, response);
            }
        }
        return "loginsuccess";
    }

    /**
     * @author lzheng
     * @date 2019/12/16
     * @return
     * @Description  激活
     **/
    @RequestMapping("/sign/verify")
    public String verify(@RequestParam("code") int code
            ,@RequestParam("id") String name
            ,HttpServletRequest request
            ,HttpServletResponse response) throws ServletException, IOException {
        Integer verifyNumber = (Integer)request.getSession().getAttribute("verifyNumber");
        if (verifyNumber==null){
            verifyNumber=1;
        }else{
            if (verifyNumber>4){
                request.getSession().setAttribute("signmsg","验证失败次数过多");
                request.getRequestDispatcher("/sign").forward(request,response);
            }
            verifyNumber++;
        }
        if(!accountService.verifyCode(name,code)){
            return "verifyfail";
        }
        return "verifysuccess";
    }

    /**
     * @author lzheng
     * @date 2019/12/17
     * @return
     * @Description 退出
     **/

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }



}
