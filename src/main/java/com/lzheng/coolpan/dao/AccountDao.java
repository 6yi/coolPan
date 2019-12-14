package com.lzheng.coolpan.dao;

import com.lzheng.coolpan.domain.Account;
import com.lzheng.coolpan.domain.Files;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    Account selectById(String name);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);


}