package com.example.flower.controller;

import com.example.flower.dto.CreateOrderDTO;
import com.example.flower.dto.SetDeliveryDTO;
import com.example.flower.dto.ChangeStateDTO;
import com.example.flower.service.OrderService;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/order")
@Api(tags = {"订单接口"})
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "分页查询订单（商家,传订单编号则查找某个订单，不传则查找全部订单）")
    @GetMapping("/findAllOrder")
    public RE findAllOrder(String orderNumber,PagePara pagePara){
        return orderService.findAllOrder(orderNumber,pagePara);
    }

    @ApiOperation(value = "分页查询订单（用户）")
    @GetMapping("/findUserOrder")
    RE findUserOrder(String keyword,PagePara pagePara){
        return orderService.findUserOrder(keyword,pagePara);
    }


    @ApiOperation(value = "为订单分配派送员（商家）")
    @PutMapping("/updateByPrimaryKey")
    public RE updateByPrimaryKey(@Validated @RequestBody SetDeliveryDTO record){
        return orderService.updateByPrimaryKey(record);
    }

    @ApiOperation(value = "创建订单（用户）")
    @PostMapping("/insertSelective")
    public RE insertSelective(@Validated @RequestBody CreateOrderDTO createOrderDTO){
        return orderService.insertSelective(createOrderDTO);
    }

    @ApiOperation(value = "修改订单状态（用户and商家）")
    @PostMapping("/updateOrderState")
    public RE updateOrderState(@Validated @RequestBody ChangeStateDTO changeStateDTO){
        return orderService.updateOrderState(changeStateDTO);
    }

    @ApiOperation(value = "派送员查看自己的待配送订单")
    @PostMapping("/findNoPaiOrder")
    public RE findNoPaiOrder(PagePara pagePara){
        return orderService.findNoPaiOrder(pagePara);
    }

    @ApiOperation(value = "派送员查看自己的配送中订单")
    @PostMapping("/findPaingOrder")
    public RE findPaingOrder(PagePara pagePara){
        return orderService.findPaingOrder(pagePara);
    }

    @ApiOperation(value = "派送员查看自己的全部订单")
    @PostMapping("/findAllPaiOrder")
    public RE findAllPaiOrder(PagePara pagePara){
        return orderService.findAllPaiOrder(pagePara);
    }

    @ApiOperation(value = "派送员查看自己的已送达的订单")
    @PostMapping("/{{ ruleForm1.createTime }}")
    public RE findPaiedOrder(PagePara pagePara){
        return orderService.findPaiedOrder(pagePara);
    }

}
