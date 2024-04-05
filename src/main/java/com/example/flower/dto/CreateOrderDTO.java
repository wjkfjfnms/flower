package com.example.flower.dto;

import com.example.flower.po.Ordergoods;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateOrderDTO {
    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id")
    private Long id;



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
     * 送达时间
     */
    @ApiModelProperty(value="送达时间")
    private String overtime;

    @ApiModelProperty(value="商品信息")
    private List<Ordergoods> orderGoodsList;

}
