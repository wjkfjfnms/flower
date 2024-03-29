package com.example.flower.controller;

import com.example.flower.service.UsersService;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/userManage")
@Api(tags = {"员工管理接口"})
public class UsersManageController {

    @Autowired
    UsersService usersService;

    @ApiOperation(value = "查询全部员工")
    @PostMapping("/selectAllUser")
    public RE selectAllUser(String name, PagePara pagePara){
        return usersService.selectAllUser(name,pagePara);
    }
}
