package com.example.flower.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.dao.GoodsMapper;
import com.example.flower.dto.AddCart;
import com.example.flower.dto.UpdateCartDTO;
import com.example.flower.po.Goods;
import com.example.flower.po.Order;
import com.example.flower.service.CommonService;
import com.example.flower.util.PageResultS;
import com.example.flower.vo.CartVO;
import com.example.flower.vo.GoodsVO;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.flower.po.Cart;
import com.example.flower.dao.CartMapper;
import com.example.flower.service.CartService;
@Service
public class CartServiceImpl implements CartService{

    @Resource
    private CartMapper cartMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    CommonService commonService;

    @Override
    public RE findAll(PagePara pagePara) {
        Long id = commonService.getUsersDetails().getId();
        // 创建 Page 对象，指定当前页和每页显示数量
        Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
        IPage<CartVO> queryResult =cartMapper.findAll(id,page, pagePara);
        // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
        PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
        // 构建 PageResultS 对象，设置查询结果列表和分页信息
        PageResultS<CartVO> result = new PageResultS<>();
        result.setList(queryResult.getRecords());
        result.setPage(pageResult);
        if (result != null){
            return RE.ok().data("CartList",result);
        }else {
            return RE.error();
        }
    }

    @Override
    public RE deleteByPrimaryKey(Long id) {
        if (cartMapper.deleteByPrimaryKey(id) !=0){
            return RE.ok();
        }else {
            return RE.error();
        }

    }

    @Override
    public int insert(Cart record) {
        return cartMapper.insert(record);
    }

    @Override
    public RE insertSelective(AddCart addCart) {
//        获取参数
        GoodsVO goods = goodsMapper.selectByPrimaryKey(addCart.getGoodsid());
        Cart cart = new Cart();
        cart.setGoodsid(addCart.getGoodsid());
        cart.setNumber(addCart.getNumber());
        Double pricesum = goods.getPrice() * addCart.getNumber();
        cart.setPricetotal(pricesum);
        cart.setUserId(addCart.getUserId());
        int re=cartMapper.insertSelective(cart);
        if (re != 0){
            return RE.ok();
        }else {
            return RE.error();
        }

    }

    @Override
    public Cart selectByPrimaryKey(Long id) {
        return cartMapper.selectByPrimaryKey(id);
    }

    @Override
    public RE updateByPrimaryKeySelective(UpdateCartDTO updateCartDTO) {
//        获取单价
        Long goodsid = cartMapper.selectByPrimaryKey(updateCartDTO.getId()).getGoodsid();
        Double price = goodsMapper.selectByPrimaryKey(goodsid).getPrice();
        Cart cart=new Cart();
        cart.setId(updateCartDTO.getId());
        cart.setNumber(updateCartDTO.getNumber());
        cart.setPricetotal(price * updateCartDTO.getNumber());
        if (cartMapper.updateByPrimaryKeySelective(cart) != 0){
            Cart result =  cartMapper.selectByPrimaryKey(cart.getId());
            return RE.ok().data("result",result);
        }else {
            return RE.error();
        }

    }

    @Override
    public int updateByPrimaryKey(Cart record) {
        return cartMapper.updateByPrimaryKey(record);
    }

}
