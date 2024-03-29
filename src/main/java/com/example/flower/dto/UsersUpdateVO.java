package com.example.flower.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.flower.vo.RealinfoVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UsersUpdateVO {
    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 身份
     */
    @ApiModelProperty(value="身份（ADMIN/USER）")
    private String role;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    private RealinfoVO realinfo;

}
