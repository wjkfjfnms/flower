package com.example.flower.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com-example-flower-po-Realinfo")
@Data
public class Realinfo {
    @ApiModelProperty(value="")
    private Integer id;

    /**
    * 该认证信息所属的用户id
    */
    @ApiModelProperty(value="该认证信息所属的用户id")
    private Integer userid;

    /**
    * 真实姓名
    */
    @ApiModelProperty(value="真实姓名")
    private String realname;

    /**
    * 身份证号码
    */
    @ApiModelProperty(value="身份证号码")
    private String identitycardnum;

    /**
    * 身份证照片正面（国徽）
    */
    @ApiModelProperty(value="身份证照片正面（国徽）")
    private String cardfront;

    /**
    * 身份证照片反面
    */
    @ApiModelProperty(value="身份证照片反面")
    private String cardback;
}