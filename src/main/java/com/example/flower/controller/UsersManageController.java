package com.example.flower.controller;

import com.example.flower.dto.AddUserDTO;
import com.example.flower.po.Users;
import com.example.flower.service.CommonService;
import com.example.flower.service.UsersService;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import com.example.flower.dto.UsersUpdateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping(value = "/api/userManage")
@Api(tags = {"员工管理接口"})
public class UsersManageController {

    @Autowired
    UsersService usersService;

    @Autowired
    CommonService commonService;

    @ApiOperation(value = "查询全部员工")
    @GetMapping("/selectAllUser")
    public RE selectAllUser(String name, PagePara pagePara){
        return usersService.selectAllUser(name,pagePara);
    }

    @ApiOperation(value = "修改某个员工信息")
    @PutMapping("/updateByPrimaryKey")
    public RE updateByPrimaryKey(@Validated @RequestBody UsersUpdateVO usersUpdateVO){
        return usersService.updateByPrimaryKey(usersUpdateVO);
    }


    @ApiOperation(value = "注销账户")
    @PutMapping("/logoutByPrimaryKey")
    public RE logoutByPrimaryKey(Integer id){
        return usersService.logoutByPrimaryKey(id);
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/addUser")
    public RE addUser(@Validated @RequestBody AddUserDTO addUserDTO){
        return usersService.addUser(addUserDTO);
    }

    @ApiOperation(value = "查询个人信息(用户)")
    @PostMapping("/findOneUser")
    public RE findOneUser(){
//        获取当前用户登录的id
        Users users = commonService.getUsersDetails();
        Long id = users.getId();
        return usersService.findOneUser(id);
    }

}
