package com.example.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.flower.po.Type;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.flower.po.Goods;
import com.example.flower.dao.GoodsMapper;

public interface GoodsService extends IService<Goods> {

    RE findAllGoods(String goodsName,PagePara pagePara);

    public RE deleteByPrimaryKey(Long id);


    public int insert(Goods record);


    public RE insertSelective(Goods record);

    public Goods selectByPrimaryKey(Long id);


    public RE updateByPrimaryKeySelective(Goods record);


    public int updateByPrimaryKey(Goods record);

}
