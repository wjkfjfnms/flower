package com.example.flower.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.dto.AddGoodsDTO;
import com.example.flower.dto.StopSalesGoodsDTO;
import com.example.flower.dto.UpdateGoodsDTO;
import com.example.flower.po.Goods;
import com.example.flower.vo.GoodsVO;
import com.example.flower.vo.PagePara;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface GoodsMapper {

//    模糊查询
    IPage<GoodsVO> findGoods(String keyword, Page<PagePara> page, @Param("par")PagePara pagePara);

//    停售
    int stopSalesGoods(StopSalesGoodsDTO stopSalesGoodsDTO);

    IPage<GoodsVO> selectByType(Long typeId,Page<PagePara> page, @Param("par")PagePara pagePara);

    IPage<GoodsVO> selectByGoodsName(String goodsName,Page<PagePara> page, @Param("par")PagePara pagePara);


    IPage<GoodsVO> findAllGoodsByPage(Page<PagePara> page, @Param("par")PagePara pagePara);

    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Goods record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(AddGoodsDTO addGoodsDTO);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    GoodsVO selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UpdateGoodsDTO updateGoodsDTO);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Goods record);
}
