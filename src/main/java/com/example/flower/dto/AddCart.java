package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddCart {

    /**
     * 商品表id
     */
    @ApiModelProperty(value="商品表id")
    private Long goodsid;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称")
    private String goodsname;

    /**
     * 商品图片
     */
    @ApiModelProperty(value="商品图片")
    private String picture;

    /**
     * 加购的商品数量
     */
    @ApiModelProperty(value="加购的商品数量")
    private Integer number;

    /**
     * 商品单价
     */
    @ApiModelProperty(value="商品单价")
    private Double price;

    /**
     * 总价
     */
    @ApiModelProperty(value="总价")
    private Double pricetotal;

    @ApiModelProperty(value="用户id")
    private Long userId;
}
