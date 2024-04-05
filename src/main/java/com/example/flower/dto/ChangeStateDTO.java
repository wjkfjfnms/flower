package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChangeStateDTO {

    @ApiModelProperty(value = "订单id")
    private Long id;

    @ApiModelProperty(value = "订单状态")
    private String state;
}
