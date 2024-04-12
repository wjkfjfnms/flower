package com.example.flower.service;

import com.example.flower.dto.ChangeStateDTO;
import com.example.flower.dto.CreateOrderDTO;
import com.example.flower.dto.SetDeliveryDTO;
import com.example.flower.po.Order;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;

public interface OrderService{

    RE findUserOrder(String keyword,PagePara pagePara);

    RE findAllOrder(String orderNumber,PagePara pagePara);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

// 创建订单
    RE insertSelective(CreateOrderDTO createOrderDTO);

    RE selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

//    设置派送员
    RE updateByPrimaryKey(SetDeliveryDTO record);

//    修改订单状态
    RE updateOrderState(ChangeStateDTO changeStateDTO);

//    派送员查看自己的待配送订单
    RE findNoPaiOrder(PagePara pagePara);

//    派送员查看自己的配送中订单
    RE findPaingOrder(PagePara pagePara);

//    派送员查看自己的全部订单（按时间顺序倒序输出）
    RE findAllPaiOrder(PagePara pagePara);

//    派送员查看自己的已送达的订单
    RE findPaiedOrder(PagePara pagePara);
}
