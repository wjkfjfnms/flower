package com.example.flower.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value="com-example-flower-po-Type")
@Data
public class Type implements Serializable {
    @ApiModelProperty(value="")
    private Long id;

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
