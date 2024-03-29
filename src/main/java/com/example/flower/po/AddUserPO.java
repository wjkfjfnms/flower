package com.example.flower.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddUserPO {
    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 加密盐
     */
    @ApiModelProperty(value="加密盐")
    private String salt;

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

    /**
     * 状态（1：已注销 0：未注销
     */
    @ApiModelProperty(value="状态（1：已注销 0：未注销")
    private Integer state;

}
