package com.example.flower.vo;

import com.example.flower.po.Ordergoods;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
public class OrderVO {
    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id")
    private Long id;

    @ApiModelProperty(value="订单编号")
    private String orderNumber;

    @ApiModelProperty(value="订单状态")
    private String state;

    @ApiModelProperty(value="下单用户名字")
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

    @ApiModelProperty(value="订单总价")
    private float price;

    @ApiModelProperty(value="下单时间")
    private Date createtime;

    /**
     * 送达时间
     */
    @ApiModelProperty(value="送达时间")
    private String overtime;

    @ApiModelProperty(value="商品信息")
    private List<Ordergoods> ordergoodsList;

    @ApiModelProperty(value="派送员")
    private String deliveryPeopleName;

    @ApiModelProperty(value="派送员电话")
    private String deliveryPeoplePhone;

//    @ApiModelProperty(value="商品名字和数量")
//    private List<Ordergoods> ordergoodsList;
}
