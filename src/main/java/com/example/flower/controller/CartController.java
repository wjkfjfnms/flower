package com.example.flower.controller;

import com.example.flower.dto.AddCart;
import com.example.flower.dto.UpdateCartDTO;
import com.example.flower.service.CartService;
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
@RequestMapping(value = "/api/cart")
@Api(tags = {"购物车接口"})
public class CartController {

    @Autowired
    CartService cartService;

    @ApiOperation(value = "添加商品到购物车")
    @PostMapping("/insertSelective")
    public RE insertSelective(@Validated @RequestBody AddCart addCart){
        return cartService.insertSelective(addCart);
    }

    @ApiOperation(value = "删除购物车的商品")
    @DeleteMapping("/deleteByPrimaryKey")
    public RE deleteByPrimaryKey(Long id){
        return cartService.deleteByPrimaryKey(id);
    }

    @ApiOperation(value = "修改购物车的商品数量")
    @PutMapping("/updateCart")
    public RE updateByPrimaryKeySelective(@Validated @RequestBody UpdateCartDTO updateCartDTO){
        return cartService.updateByPrimaryKeySelective(updateCartDTO);
    }

    @ApiOperation(value = "分页查询用户购物车")
    @GetMapping("/findAll")
    public RE findAll(PagePara pagePara){
        return cartService.findAll(pagePara);
    }
}
