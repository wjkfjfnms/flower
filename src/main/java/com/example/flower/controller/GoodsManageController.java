package com.example.flower.controller;


import com.example.flower.dto.AddGoodsDTO;
import com.example.flower.dto.StopSalesGoodsDTO;
import com.example.flower.po.Goods;
import com.example.flower.service.GoodsService;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value = "/api/goods")
@Api(tags = {"商品管理接口"})
public class GoodsManageController {
    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "添加商品")
    @PostMapping("/addGoods")
    public RE addGoods(AddGoodsDTO addGoodsDTO,MultipartFile file){
        System.out.println("dto:" + addGoodsDTO);
        System.out.println("file:" + file);
        return goodsService.insertSelective(addGoodsDTO,file);
    }

    @ApiOperation(value = "修改商品信息")
    @PutMapping("/updateGoods")
    public RE updateByPrimaryKey(@Validated @RequestBody Goods goods){
        return goodsService.updateByPrimaryKeySelective(goods);
    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping("/deleteGoods")
    public RE deleteByPrimaryKey(Long id){
        return goodsService.deleteByPrimaryKey(id);
    }

    @ApiOperation(value = "分页查询全部商品")
    @GetMapping("/selectAllGoods")
    public RE selectAllGoods(String goodsName,PagePara pagePara){
        return goodsService.findAllGoods(goodsName,pagePara);
    }

    @ApiOperation(value = "查询某个商品信息")
    @GetMapping("/selectByPrimaryKey")
    public RE selectByPrimaryKey(Long id){
        return goodsService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "根据销量和分类显示商品")
    @GetMapping("/selectByType")
    public RE selectByType(Long typeId,PagePara pagePara){
        return goodsService.selectByType(typeId,pagePara);
    }

    @ApiOperation(value = "停售商品")
    @PutMapping("/stopSalesGoods")
    public RE stopSalesGoods(@Validated @RequestBody StopSalesGoodsDTO stopSalesGoodsDTO){
        return goodsService.stopSalesGoods(stopSalesGoodsDTO);
    }

    @ApiOperation(value = "回复销售商品")
    @PutMapping("/reSalesGoods")
    RE reSalesGoods(@Validated @RequestBody StopSalesGoodsDTO stopSalesGoodsDTO){
        return goodsService.reSalesGoods(stopSalesGoodsDTO);
    }

}
