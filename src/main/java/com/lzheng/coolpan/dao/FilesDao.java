package com.lzheng.coolpan.dao;

import com.lzheng.coolpan.domain.Files;

import java.util.List;

public interface FilesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);

    List<Files> findByAccountId(Integer id);
    List<Files> findByType(Integer id,Integer type);
}