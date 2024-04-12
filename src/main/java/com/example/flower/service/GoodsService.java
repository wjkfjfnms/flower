package com.example.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.flower.dto.AddGoodsDTO;
import com.example.flower.dto.StopSalesGoodsDTO;
import com.example.flower.dto.UpdateGoodsDTO;
import com.example.flower.po.Type;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.flower.po.Goods;
import com.example.flower.dao.GoodsMapper;
import org.springframework.web.multipart.MultipartFile;

public interface GoodsService extends IService<Goods> {

//    停售
    RE stopSalesGoods(StopSalesGoodsDTO stopSalesGoodsDTO);

//    恢复销售
    RE reSalesGoods(StopSalesGoodsDTO stopSalesGoodsDTO);


    RE selectByType(Long typeId,PagePara pagePara);

    RE findAllGoods(String goodsName,PagePara pagePara);

    RE userFindAllGoods(PagePara pagePara);

    public RE deleteByPrimaryKey(Long id);


    public int insert(Goods record);


    public RE insertSelective(AddGoodsDTO addGoodsDTO,MultipartFile file);

    public RE selectByPrimaryKey(Long id);


    public RE updateByPrimaryKeySelective(UpdateGoodsDTO updateGoodsDTO, MultipartFile file);


    public int updateByPrimaryKey(Goods record);

    RE findGoods(String keyword,PagePara pagePara);

}
