package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SetDeliveryDTO {

    @ApiModelProperty(value = "派送员id")
    private Long id;

    @ApiModelProperty(value = "派送员名字")
    private String deliveryPeopleName;

    @ApiModelProperty(value = "派送员电话")
    private String deliveryPeoplePhone;


}
