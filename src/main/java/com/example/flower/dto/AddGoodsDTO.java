package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class AddGoodsDTO {
    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id(不用传)")
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

    /**
     * 图片
     */
    @ApiModelProperty(value="图片")
    private MultipartFile file;
}
