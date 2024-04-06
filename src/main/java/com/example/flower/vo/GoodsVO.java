package com.example.flower.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsVO {
    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id")
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称")
    private String goodsname;

    /**
     * 库存数量
     */
    @ApiModelProperty(value="库存数量")
    private Integer inventory;

    /**
     * 商品图片
     */
    @ApiModelProperty(value="商品图片")
    private String picture;

    /**
     * 所属类别id
     */
    @ApiModelProperty(value="所属类别id")
    private Long typeid;

    /**
     * 分类名称
     */
    @ApiModelProperty(value="分类名称")
    private String typename;

    /**
     * 商品价格
     */
    @ApiModelProperty(value="商品价格")
    private Double price;

    /**
     * 售卖状态
     */
    @ApiModelProperty(value="售卖状态")
    private String state;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createtimes;

    /**
     * 销量
     */
    @ApiModelProperty(value="销量")
    private Double sales;
}
