package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddTypeDTO {
    /**
     * 分类名称
     */
    @ApiModelProperty(value="分类名称")
    private String typename;

    /**
     * 分类类型
     */
    @ApiModelProperty(value="分类类型")
    private String typec;

    /**
     * 分类图片
     */
    @ApiModelProperty(value="分类图片")
    private String typeimage;
}
