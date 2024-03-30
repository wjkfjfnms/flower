package com.example.flower.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 购物车
    */
@ApiModel(value="com-example-flower-po-Cart")
@Data
public class Cart {
    @ApiModelProperty(value="")
    private Long id;

    /**
    * 商品表id
    */
    @ApiModelProperty(value="商品表id")
    private Long goodsid;

    /**
    * 加购的商品数量
    */
    @ApiModelProperty(value="加购的商品数量")
    private Integer number;

    /**
    * 总价
    */
    @ApiModelProperty(value="总价")
    private Double pricetotal;

    @ApiModelProperty(value="用户id")
    private Long userId;
}
