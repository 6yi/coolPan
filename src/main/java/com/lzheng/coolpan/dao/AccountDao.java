package com.lzheng.coolpan.dao;

import com.lzheng.coolpan.domain.Account;
import org.springframework.stereotype.Component;

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