package com.example.flower.controller;

import com.example.flower.dto.*;
import com.example.flower.service.CommonService;
import com.example.flower.service.UsersService;
import com.example.flower.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@Api(tags = {"登录注册接口"})
public class LoginController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private CommonService commonService;


    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public RE register(@RequestBody RegisterDTO registerDTO) {
        return usersService.insert(registerDTO);
    }

    @ApiOperation(value = "密码登录")
    @PostMapping("/passwordLogin")
    public RE passwordLogin(@Validated @RequestBody PasswordLoginDTO passwordLoginDTO){
        return usersService.passwordLogin(passwordLoginDTO);
    }

    @ApiOperation(value = "验证码登录")
    @PostMapping("/codeLogin")
    public RE codeLogin(@Validated @RequestBody CodeLoginDTO codeLoginDTO){
        return usersService.codeLogin(codeLoginDTO);
    }

    @ApiOperation(value = "找回密码")
    @PostMapping("/findPassword")
    public RE findPassword(@Validated @RequestBody GetEmailCodeDTO getEmailCodeDTO){
        return usersService.findPassword(getEmailCodeDTO);
    }


    @ApiOperation(value = "权限码请求接口")
    @PostMapping("code/getRequestPermissionCode")
    public RE getRequestPermissionCode(@RequestBody QuanxianmaDTO quanxianmaDTO) {
        return commonService.getRequestPermissionCode(quanxianmaDTO.getEmailJson());
    }


    @ApiOperation(value = "邮箱验证码接口")
    @PostMapping("code/sendEmailCode")
    public RE sendEmailCode(@RequestBody GetEmailCodeDTO getEmailCodeDTO) {
        return commonService.sendEmailCode(getEmailCodeDTO);
    }

    //    /** 这是管理员用户才可以看到 */
//    @PostMapping(value = "/admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String admin() {
//        return "这个消息只有管理员用户才可以看到";
//    }
//
//    /** 这是登录用户才可以看到的内容 */
//    @PostMapping(value = "/message")
//    public String message() {
//        return "这个消息只有登录用户才可以看到";
//    }
}
