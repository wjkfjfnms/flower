package com.example.flower.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.po.Order;
import com.example.flower.vo.PagePara;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface OrderMapper {



    IPage<Order> findAllOrder(Page<PagePara> page, @Param("par")PagePara pagePara);

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
    int insert(Order record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Order record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Order selectByPrimaryKey(Long id);

    Order selectByOrderNumber(String orderNumber);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Order record);
}
