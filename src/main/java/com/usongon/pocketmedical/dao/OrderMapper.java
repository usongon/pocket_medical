package com.usongon.pocketmedical.dao;

import com.usongon.pocketmedical.bean.entity.Order;
import com.usongon.pocketmedical.bean.param.OrderGetListParams;
import com.usongon.pocketmedical.bean.result.OrderDetailResult;
import com.usongon.pocketmedical.bean.result.OrderListResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yxp
 * @date 2020/5/13
 */
@Mapper
public interface OrderMapper {
    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByOrderDepartmentAndOrderDoctor(@Param("orderDepartment") String orderDepartment, @Param("orderDoctor") String orderDoctor);

    List<OrderListResult> getOrderList(OrderGetListParams params);

    void updateOrderStateByOrderId(@Param("updatedOrderState")String updatedOrderState,@Param("orderId")String orderId);

    OrderDetailResult getOrderDetail(String orderId);


}