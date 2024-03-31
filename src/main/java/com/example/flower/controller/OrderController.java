package com.example.flower.controller;

import com.example.flower.service.OrderService;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/order")
@Api(tags = {"订单接口"})
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "分页查询订单（商家,传订单编号则查找某个订单，不传则查找全部订单）")
    @PostMapping("/findAllOrder")
    public RE findAllOrder(String orderNumber,PagePara pagePara){
        return orderService.findAllOrder(orderNumber,pagePara);
    }

    @ApiOperation(value = "分页查询订单（用户）")
    @PostMapping("/findUserOrder")
    RE findUserOrder(PagePara pagePara){
        return orderService.findUserOrder(pagePara);
    }
}
