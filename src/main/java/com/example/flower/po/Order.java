package com.example.flower.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
    * 订单
    */
@ApiModel(value="com-example-flower-po-Order")
@Data
public class Order {
    /**
    * 订单id
    */
    @ApiModelProperty(value="订单id")
    private Long id;

    /**
    * 订单编号
    */
    @ApiModelProperty(value="订单编号")
    private String ordernumber;

    /**
    * 订单状态
    */
    @ApiModelProperty(value="订单状态")
    private String state;

    /**
    * 所属用户
    */
    @ApiModelProperty(value="所属用户")
    private Long userid;

    @ApiModelProperty(value="用户名字")
    private String nickname;

    @ApiModelProperty(value="收货人名字")
    private String name;
    /**
    * 电话
    */
    @ApiModelProperty(value="电话")
    private String phone;

    /**
    * 收货地址
    */
    @ApiModelProperty(value="收货地址")
    private String address;

    /**
    * 实付金额
    */
    @ApiModelProperty(value="实付金额")
    private Double price;

    /**
    * 下单时间
    */
    @ApiModelProperty(value="下单时间")
    private Date createtime;

    /**
    * 送达时间
    */
    @ApiModelProperty(value="送达时间")
    private String overtime;

    @ApiModelProperty(value="派送员名字")
    private String deliveryPeopleName;

    @ApiModelProperty(value="派送员电话")
    private String deliveryPeoplePhone;
}
