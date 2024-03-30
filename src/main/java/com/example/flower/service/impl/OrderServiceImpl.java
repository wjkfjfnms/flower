package com.example.flower.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.po.Goods;
import com.example.flower.util.PageResultS;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.flower.dao.OrderMapper;
import com.example.flower.po.Order;
import com.example.flower.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public RE findAllOrder(String orderNumber,PagePara pagePara) {
        if (orderNumber != null && !orderNumber.equals("")){
//            查询某个订单
            return RE.ok().data("Order",orderMapper.selectByOrderNumber(orderNumber));
        }else {
            // 创建 Page 对象，指定当前页和每页显示数量
            Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
            IPage<Order> queryResult =orderMapper.findAllOrder(page, pagePara);
            // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
            PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
            // 构建 PageResultS 对象，设置查询结果列表和分页信息
            PageResultS<Order> result = new PageResultS<>();
            result.setList(queryResult.getRecords());
            result.setPage(pageResult);
            if (result != null){
                return RE.ok().data("OrderList",result);
            }else {
                return RE.error();
            }
        }

    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    public Order selectByPrimaryKey(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }

}
