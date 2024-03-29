package com.example.flower.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.po.Type;
import com.example.flower.vo.PagePara;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface TypeMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param type the record
     * @return insert count
     */
    int insert(Type type);

    /**
     * 查询全部分类
     * @return
     */
    IPage<Type> selectAllType(Page<PagePara> page, @Param("par")PagePara pagePara);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Type record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Type selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Type record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Type record);
}
