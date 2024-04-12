package com.example.flower.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.dao.CartMapper;
import com.example.flower.dao.OrdergoodsMapper;
import com.example.flower.dto.ChangeStateDTO;
import com.example.flower.dto.CreateOrderDTO;
import com.example.flower.dto.SetDeliveryDTO;
import com.example.flower.po.Ordergoods;
import com.example.flower.po.Users;
import com.example.flower.service.CommonService;
import com.example.flower.util.PageResultS;
import com.example.flower.vo.OrderVO;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.flower.dao.OrderMapper;
import com.example.flower.po.Order;
import com.example.flower.service.OrderService;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private OrdergoodsMapper ordergoodsMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public RE findUserOrder(String keyword,PagePara pagePara) {
        if (keyword == null || keyword.equals("")){
            //        获取用户id
            Users users = commonService.getUsersDetails();
            // 创建 Page 对象，指定当前页和每页显示数量
            Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
            IPage<Order> queryResult =orderMapper.findUserOrder(users.getId(),page, pagePara);
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

        // 创建 Page 对象，指定当前页和每页显示数量
        Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
        IPage<Order> queryResult =orderMapper.selectByOrderNumber(keyword,page, pagePara);
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

    @Override
    public RE findAllOrder(String orderNumber,PagePara pagePara) {
        if (orderNumber != null && !orderNumber.equals("")){
//            查询某个订单
            // 创建 Page 对象，指定当前页和每页显示数量
            Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
            IPage<Order> queryResult =orderMapper.selectByOrderNumber(orderNumber,page, pagePara);
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
    public RE insertSelective(CreateOrderDTO createOrderDTO) {
//        获取参数
        Order order = new Order();
//        生成订单编号
        StringBuilder randomNum = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            randomNum.append(ThreadLocalRandom.current().nextInt(10));
        }
        order.setOrdernumber(String.valueOf(randomNum)); //订单编号
        order.setAddress(createOrderDTO.getAddress()); //收货地址
        order.setPhone(createOrderDTO.getPhone()); //收货人电话
        order.setUserid(commonService.getUsersDetails().getId()); //下单用户的id
//        获取商品价格
        double price = 0;
        for (Ordergoods ordergoods : createOrderDTO.getOrderGoodsList()){
            price += (ordergoods.getGoodsprice() * ordergoods.getGoodsnum());
        }
        order.setPrice(price); //订单总价
        order.setState("已付款");
        order.setOvertime(createOrderDTO.getOvertime()); //送达时间
        order.setName(createOrderDTO.getName());
        if (orderMapper.insertSelective(order) != 0){
//            订单商品表
        for (Ordergoods ordergoods : createOrderDTO.getOrderGoodsList()){
            ordergoods.setOrderid(order.getId());
            ordergoodsMapper.insertSelective(ordergoods);
        }
//        删除购物车中的商品
            cartMapper.deleteByPrimaryKey(createOrderDTO.getId());
            OrderVO result = orderMapper.selectByPrimaryKey(order.getId());
            return RE.ok().data("result",result);
        }
        return RE.error();
    }

    @Override
    public RE selectByPrimaryKey(Long id) {
        return RE.ok().data("result",orderMapper.selectByPrimaryKey(id));
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RE updateByPrimaryKey(SetDeliveryDTO record) {
        if (orderMapper.updateByPrimaryKey(record) != 0){
            OrderVO order = orderMapper.selectByPrimaryKey(record.getId());
            return RE.ok().data("result",order);
        }
        return RE.error();
    }

    @Override
    public RE updateOrderState(ChangeStateDTO changeStateDTO) {
        if (orderMapper.updateOrderState(changeStateDTO) != 0){
            OrderVO order = orderMapper.selectByPrimaryKey(changeStateDTO.getId());
            return RE.ok().data("result",order);
        }
        return RE.error();
    }

}
