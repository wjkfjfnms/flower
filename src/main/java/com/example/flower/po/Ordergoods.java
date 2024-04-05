package com.example.flower.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com-example-flower-po-Ordergoods")
@Data
public class Ordergoods {
    @ApiModelProperty(value="")
    private Long id;

    /**
    * 订单id
    */
    @ApiModelProperty(value="订单id")
    private Long orderid;

    /**
    * 商品id
    */
    @ApiModelProperty(value="商品id")
    private Long goodsid;

    /**
    * 商品单价
    */
    @ApiModelProperty(value="商品单价")
    private Double goodsprice;

    /**
    * 商品数量
    */
    @ApiModelProperty(value="商品数量")
    private Integer goodsnum;

    /**
    * 商品名字
    */
    @ApiModelProperty(value="商品名字")
    private String goodsName;
}
