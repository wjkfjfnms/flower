package com.example.flower.service;

import com.example.flower.dto.AddCart;
import com.example.flower.dto.UpdateCartDTO;
import com.example.flower.po.Cart;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;

public interface CartService{

    RE findAll(PagePara pagePara);

    RE deleteByPrimaryKey(Long id);

    int insert(Cart record);

    RE insertSelective(AddCart addCart);

    Cart selectByPrimaryKey(Long id);

    RE updateByPrimaryKeySelective(UpdateCartDTO updateCartDTO);

    int updateByPrimaryKey(Cart record);

}
