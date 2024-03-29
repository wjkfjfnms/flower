package com.example.flower.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddUserDTO {
    /**
     * 用户账号(邮箱)
     */
    @ApiModelProperty(value="用户账号(邮箱)")
    private String email;


    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private String sex;

    /**
     * 联系方式
     */
    @ApiModelProperty(value="联系方式")
    private String phone;

    /**
     * 角色
     */
    @ApiModelProperty(value="角色（Florists/delivery/users）")
    private String role;

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
}
