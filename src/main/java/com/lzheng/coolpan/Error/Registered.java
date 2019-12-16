package com.lzheng.coolpan.Error;

/**
 * @ClassName Registered
 * @Author 刘正
 * @Date 2019/12/16 15:56
 * @Version 1.0
 * @Description:
 */


public class Registered extends AccountError {
    @Override
    public String getMessage() {
        return "该账户已注册";
    }
}
