package com.example.flower.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CartVO {
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

    /**
     * 商品价格
     */
    @ApiModelProperty(value="商品价格")
    private Double price;
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
}
