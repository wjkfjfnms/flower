package com.example.flower.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.flower.dto.RegisterDTO;
import com.example.flower.po.Users;
import com.example.flower.vo.userVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsersMapper extends BaseMapper<Users> {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert registerDTO to table
     * @param registerDTO
     * @return insert id
     */
    int insert(RegisterDTO registerDTO);

    /**
     * select by email
     * @param email
     * @return userVO(id,email,password,role)
     */
    userVO selectByEmail(String email);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Users record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Users selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Users record);
}
