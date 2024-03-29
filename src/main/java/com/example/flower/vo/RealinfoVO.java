package com.example.flower.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RealinfoVO {
    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    private String realname;
}
