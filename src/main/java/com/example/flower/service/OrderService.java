package com.example.flower.service;

import com.example.flower.po.Order;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;

public interface OrderService{

    RE findAllOrder(String orderNumber,PagePara pagePara);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

}
