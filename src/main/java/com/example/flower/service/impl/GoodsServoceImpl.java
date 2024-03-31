package com.example.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.dao.GoodsMapper;
import com.example.flower.dto.AddGoodsDTO;
import com.example.flower.dto.StopSalesGoodsDTO;
import com.example.flower.po.Goods;
import com.example.flower.po.Type;
import com.example.flower.service.GoodsService;
import com.example.flower.util.PageResultS;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class GoodsServoceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Autowired
    UploadImageServiceImpl uploadImageService;


    @Override
    public RE stopSalesGoods(StopSalesGoodsDTO stopSalesGoodsDTO) {
        stopSalesGoodsDTO.setState("停售");
        if (goodsMapper.stopSalesGoods(stopSalesGoodsDTO) != 0){
            Goods goods = goodsMapper.selectByPrimaryKey(stopSalesGoodsDTO.getId());
            return RE.ok().data("result",goods);
        }
        return RE.error();
    }

    @Override
    public RE reSalesGoods(StopSalesGoodsDTO stopSalesGoodsDTO) {
        stopSalesGoodsDTO.setState("在售");
        if (goodsMapper.stopSalesGoods(stopSalesGoodsDTO) != 0){
            Goods goods = goodsMapper.selectByPrimaryKey(stopSalesGoodsDTO.getId());
            return RE.ok().data("result",goods);
        }
        return RE.error();
    }

    @Override
    public RE selectByType(Long typeId, PagePara pagePara) {
        // 创建 Page 对象，指定当前页和每页显示数量
        Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
        IPage<Goods> queryResult =goodsMapper.selectByType(typeId,page, pagePara);
        // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
        PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
        // 构建 PageResultS 对象，设置查询结果列表和分页信息
        PageResultS<Goods> result = new PageResultS<>();
        result.setList(queryResult.getRecords());
        result.setPage(pageResult);
        if (result != null){
            return RE.ok().data("GoodsList",result);
        }else {
            return RE.error();
        }
    }

    @Override
    public RE findAllGoods(String goodsName,PagePara pagePara) {
        if (goodsName != null && !goodsName.equals("")){
//            查询某个订单
            return RE.ok().data("Order",goodsMapper.selectByGoodsName(goodsName));
        }else {
            // 创建 Page 对象，指定当前页和每页显示数量
            Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
            IPage<Goods> queryResult =goodsMapper.findAllGoods(page, pagePara);
            // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
            PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
            // 构建 PageResultS 对象，设置查询结果列表和分页信息
            PageResultS<Goods> result = new PageResultS<>();
            result.setList(queryResult.getRecords());
            result.setPage(pageResult);
            if (result != null){
                return RE.ok().data("GoodsList",result);
            }else {
                return RE.error();
            }
        }
    }

    @Override
    public RE deleteByPrimaryKey(Long id) {
        int re = goodsMapper.deleteByPrimaryKey(id);
        if (re!=0){
            return RE.ok();
        }else {
            return RE.error();
        }
    }


    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }


    @Override
    public RE insertSelective(AddGoodsDTO addGoodsDTO) {
        if (addGoodsDTO != null){
            if (addGoodsDTO.getFile() == null || addGoodsDTO.getFile().isEmpty()) {
                return RE.error().message("图片上传失败");
            }
//            调用方法生成图片路径
            String imagePath = uploadImageService.upload(addGoodsDTO.getFile());
            // 将图片路径设置到 record 对象的 picture 属性中
            addGoodsDTO.setPicture(imagePath);
            addGoodsDTO.setState("在售");
            int re = goodsMapper.insertSelective(addGoodsDTO);
            if (re != 0){
                Goods result = goodsMapper.selectByPrimaryKey(addGoodsDTO.getId());
                return RE.ok().data("goods",result);
            }else {
                return RE.error();
            }
        }else {
            return RE.error().message("参数为空！");
        }
    }


    @Override
    public RE selectByPrimaryKey(Long id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        if (goods != null){
            return RE.ok().data("result",goods);
        }
        return RE.error();
    }


    @Override
    public RE updateByPrimaryKeySelective(Goods record) {
        if (record != null){
            int re = goodsMapper.updateByPrimaryKeySelective(record);
            if (re != 0){
                Goods goods = goodsMapper.selectByPrimaryKey(record.getId());
                return RE.ok().data("result",goods);
            }else {
                return RE.error();
            }
        }else {
            return RE.error().message("参数为空！");
        }

    }


    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean saveBatch(Collection<Goods> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Goods> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Goods> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Goods entity) {
        return false;
    }

    @Override
    public Goods getOne(Wrapper<Goods> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Goods> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Goods> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Goods> getBaseMapper() {
        return null;
    }
}
