package com.example.flower.service;

import com.example.flower.dto.GetEmailCodeDTO;
import com.example.flower.po.Users;
import com.example.flower.vo.RE;

public interface CommonService {

    /**
     * 获取请求权限码
     * @param emailJson 邮箱
     * @return
     */
    RE getRequestPermissionCode(String emailJson);

    /**
     * 发送邮箱验证码
     * @param getEmailCodeDTO （邮箱和权限码）
     * @return
     */
    RE sendEmailCode(GetEmailCodeDTO getEmailCodeDTO);

    /**
     * 根据token里的邮箱获取昵称
     * @param
     * @return nickname
     */
    String getTokenNickname();

    /**
     * 根据token里的邮箱获取用户信息
     * @param
     * @return id, email, password, sex, phone, touxiang, createtime, role ,nickname
     */
    Users getUsersDetails();
}

