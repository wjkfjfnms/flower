package com.example.flower.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.flower.dao.OrdergoodsMapper;
import com.example.flower.po.Ordergoods;
import com.example.flower.service.OrdergoodsService;
@Service
public class OrdergoodsServiceImpl implements OrdergoodsService{

    @Resource
    private OrdergoodsMapper ordergoodsMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return ordergoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Ordergoods record) {
        return ordergoodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Ordergoods record) {
        return ordergoodsMapper.insertSelective(record);
    }

    @Override
    public Ordergoods selectByPrimaryKey(Long id) {
        return ordergoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Ordergoods record) {
        return ordergoodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Ordergoods record) {
        return ordergoodsMapper.updateByPrimaryKey(record);
    }

}
