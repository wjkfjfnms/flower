package com.example.flower.controller;

import com.example.flower.dto.AddTypeDTO;
import com.example.flower.dto.FlowerTypeDto;
import com.example.flower.enums.flowerTypeEnum;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/typeManage")
@Api(tags = {"分类管理接口"})
public class TypeManageController {

    @Autowired
    TypeService typeService;

    @ApiOperation(value = "添加分类")
    @PostMapping("/addType")
    public RE addType(AddTypeDTO addTypeDTO, MultipartFile file){
        return typeService.addType(addTypeDTO,file);
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
    public RE updateType(Type type, MultipartFile file){
        return typeService.updateType(type,file);
    }

//    @ApiOperation(value = "获取分类类别的下拉列表数据")
//    @GetMapping("/getFlowerTypes")
//    public List<FlowerTypeDto> getFlowerTypes() {
//        return Arrays.stream(flowerTypeEnum.values())
//                .map(FlowerTypeDto::new)
//                .collect(Collectors.toList());
//    }

    @ApiOperation(value = "获取分类类别的下拉列表数据")
    @GetMapping("/getFlowerTypes")
    public RE getFlowerTypes(){
        return typeService.getType();
    }


}
