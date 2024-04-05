package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateGoodsDTO {
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
    @ApiModelProperty(value="商品图片(不传这里)")
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



//    /**
//     * 图片
//     */
//    @ApiModelProperty(value="图片(传这里)")
//    private MultipartFile file;
}
