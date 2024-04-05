package com.example.flower.service;

import com.example.flower.po.Ordergoods;
public interface OrdergoodsService{


    int deleteByPrimaryKey(Long id);

    int insert(Ordergoods record);

    int insertSelective(Ordergoods record);

    Ordergoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ordergoods record);

    int updateByPrimaryKey(Ordergoods record);

}
