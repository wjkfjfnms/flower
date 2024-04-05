package com.example.flower.dao;

import com.example.flower.dto.CreateOrderDTO;
import com.example.flower.po.Ordergoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdergoodsMapper {
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
    int insert(Ordergoods record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Ordergoods record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Ordergoods selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Ordergoods record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Ordergoods record);
}
