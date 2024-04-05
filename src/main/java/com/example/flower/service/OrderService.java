package com.example.flower.service;

import com.example.flower.dto.ChangeStateDTO;
import com.example.flower.dto.CreateOrderDTO;
import com.example.flower.dto.SetDeliveryDTO;
import com.example.flower.po.Order;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;

public interface OrderService{

    RE findUserOrder(PagePara pagePara);

    RE findAllOrder(String orderNumber,PagePara pagePara);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

// 创建订单
    RE insertSelective(CreateOrderDTO record);

    RE selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

//    设置派送员
    RE updateByPrimaryKey(SetDeliveryDTO record);

//    修改订单状态
    RE updateOrderState(ChangeStateDTO changeStateDTO);

}
