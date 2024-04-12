package com.example.flower.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.flower.dto.ChangeStateDTO;
import com.example.flower.dto.SetDeliveryDTO;
import com.example.flower.po.Order;
import com.example.flower.vo.OrderVO;
import com.example.flower.vo.PagePara;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface OrderMapper {


    IPage<Order> findUserOrder(Long userid,Page<PagePara> page, @Param("par")PagePara pagePara);


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
    OrderVO selectByPrimaryKey(Long id);

    IPage<Order> selectByOrderNumber(String orderNumber,Page<PagePara> page, @Param("par")PagePara pagePara);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 设置派送员
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SetDeliveryDTO record);


//    修改订单状态
    int updateOrderState(ChangeStateDTO changeStateDTO);

    //    派送员查看自己的待配送订单
    IPage<Order> findNoPaiOrder(Long id,Page<PagePara> page, @Param("par")PagePara pagePara);
//    派送员查看自己的配送中订单
    IPage<Order> findPaingOrder(Long id,Page<PagePara> page, @Param("par")PagePara pagePara);
//    派送员查看自己的全部订单（按时间顺序倒序输出）
    IPage<Order> findAllPaiOrder(Long id,Page<PagePara> page, @Param("par")PagePara pagePara);
//
    IPage<Order> findPaiedOrder(Long id,Page<PagePara> page, @Param("par")PagePara pagePara);

}
