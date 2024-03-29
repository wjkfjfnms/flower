package com.example.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.dao.GoodsMapper;
import com.example.flower.po.Goods;
import com.example.flower.po.Type;
import com.example.flower.service.GoodsService;
import com.example.flower.util.PageResultS;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class GoodsServoceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;


    @Override
    public RE findAllGoods(PagePara pagePara) {
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
    public RE insertSelective(Goods record) {
        if (record != null){
            int re = goodsMapper.insertSelective(record);
            if (re != 0){
                return RE.ok();
            }else {
                return RE.error();
            }
        }else {
            return RE.error().message("参数为空！");
        }
    }


    @Override
    public Goods selectByPrimaryKey(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    @Override
    public RE updateByPrimaryKeySelective(Goods record) {
        if (record != null){
            int re = goodsMapper.updateByPrimaryKeySelective(record);
            if (re != 0){
                return RE.ok();
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