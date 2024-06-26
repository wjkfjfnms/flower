package com.example.flower.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CodeLoginDTO {
    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号(邮箱)
     */
    @ApiModelProperty(value="用户账号(邮箱)", dataType = "String", required = true)
    @Schema(example = "xxx@qq.com")
    private String email;

    /**
     * 验证码
     */
    @ApiModelProperty(value="验证码", dataType = "String", required = true)
    @Schema(example = "123456")
    private String code;
}
