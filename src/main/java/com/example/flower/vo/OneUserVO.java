package com.example.flower.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.flower.po.Realinfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OneUserVO {
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
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String touxiang;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createtime;

    /**
     * 身份
     */
    @ApiModelProperty(value="身份（ADMIN/USER）")
    private String role;

    /**
     * 昵称（10个字以内)
     */
    @ApiModelProperty(value="昵称（10个字以内)")
    private String nickname;

    /**
     * 状态（1：已注销 0：未注销
     */
    @ApiModelProperty(value="状态（1：已注销 0：未注销")
    private Integer state;

    @ApiModelProperty(value="实名信息")
    private List<Realinfo> realinfoList;
}
