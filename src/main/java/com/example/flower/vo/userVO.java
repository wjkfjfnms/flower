package com.example.flower.vo;

import lombok.Data;

import java.util.Date;

@Data
public class userVO {
    private Long id;
    private String email;
    private String realname;
    private String role;
    private String sex;
    private String phone;
    private Date createtime;
    private Integer state;
    private String nickname;
    private String password;
}
