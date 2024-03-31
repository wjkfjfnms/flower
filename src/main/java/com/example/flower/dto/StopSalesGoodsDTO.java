package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StopSalesGoodsDTO {
    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id")
    private Long id;

    /**
     * 售卖状态
     */
    @ApiModelProperty(value="售卖状态")
    private String state;
}
