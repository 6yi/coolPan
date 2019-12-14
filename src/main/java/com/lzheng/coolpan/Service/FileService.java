package com.lzheng.coolpan.Service;

import com.lzheng.coolpan.domain.Account;
import org.springframework.stereotype.Component;

/**
 * @ClassName FileService
 * @Author 刘正
 * @Date 2019/12/14 16:22
 * @Version 1.0
 * @Description:
 */

@Component
public class FileService {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
