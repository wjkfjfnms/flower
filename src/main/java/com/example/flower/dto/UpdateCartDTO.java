package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateCartDTO {

    @ApiModelProperty(value="")
    private Long id;

    /**
     * 加购的商品数量
     */
    @ApiModelProperty(value="加购的商品数量")
    private Integer number;
}
