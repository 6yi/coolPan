package com.lzheng.coolpan.Error;

/**
 * @ClassName notActive
 * @Author 刘正
 * @Date 2019/12/16 15:55
 * @Version 1.0
 * @Description:
 */


public class notActive extends AccountError {
    @Override
    public String getMessage() {
        return "该账户未激活";
    }
}
