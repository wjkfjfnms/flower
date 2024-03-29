package com.example.flower.dao;

import com.example.flower.po.Realinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RealinfoMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Realinfo record);

    /**
     * insert record to table selective
     * @param realinfo the record
     * @return insert count
     */
    int AddUser(Realinfo realinfo);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Realinfo selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Realinfo record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Realinfo record);
}
