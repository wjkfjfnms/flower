package com.example.flower.controller;

import com.example.flower.dto.AddTypeDTO;
import com.example.flower.po.Type;
import com.example.flower.service.TypeService;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/typeManage")
@Api(tags = {"分类管理接口"})
public class TypeManageController {

    @Autowired
    TypeService typeService;

    @ApiOperation(value = "添加分类")
    @PostMapping("/addType")
    public RE addType(@Validated @RequestBody AddTypeDTO addTypeDTO){
        return typeService.addType(addTypeDTO);
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping("/deleteType")
    public RE deleteType(Long id){
        return typeService.deleteType(id);
    }


    @ApiOperation(value = "分页查询全部分类")
    @GetMapping("/selectAllType")
    public RE selectAllType(PagePara pagePara){
        return typeService.selectAllType(pagePara);
    }

    @ApiOperation(value = "修改分类信息")
    @PutMapping("/updateType")
    public RE updateType(@Validated @RequestBody Type type){
        return typeService.updateType(type);
    }


}
