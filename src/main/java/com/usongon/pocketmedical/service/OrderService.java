package com.usongon.pocketmedical.service;

import java.util.List;
import com.usongon.pocketmedical.bean.entity.Order;
import com.usongon.pocketmedical.bean.param.OrderGetListParams;
import com.usongon.pocketmedical.bean.param.OrderInsertParams;
import com.usongon.pocketmedical.bean.result.OrderDetailResult;
import com.usongon.pocketmedical.bean.result.OrderListResult;

/**
 *
 * @author yxp
 * @date 2020/5/13
 */
public interface OrderService{


    int insert(Order record);

    int insertSelective(OrderInsertParams params);

    List<Order> selectByOrderDepartmentAndOrderDoctor(String orderDepartment,String orderDoctor);

    List<OrderListResult> getOrderList(OrderGetListParams params);

    void updateOrderStateByOrderId(String updatedOrderState, String orderId);

    OrderDetailResult getOrderDetail(String orderId);
}
