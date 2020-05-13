package com.usongon.pocketmedical.service.impl;

import com.sun.tools.corba.se.idl.constExpr.Or;
import com.usongon.pocketmedical.bean.param.OrderGetListParams;
import com.usongon.pocketmedical.bean.param.OrderInsertParams;
import com.usongon.pocketmedical.bean.result.OrderDetailResult;
import com.usongon.pocketmedical.bean.result.OrderListResult;
import com.usongon.pocketmedical.common.utils.DateUtil;
import com.usongon.pocketmedical.common.utils.UuidUtil;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import com.usongon.pocketmedical.dao.OrderMapper;
import com.usongon.pocketmedical.bean.entity.Order;
import com.usongon.pocketmedical.service.OrderService;
/**
 *
 * @author zhangdehua
 * @date 2020/5/13
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderInsertParams params) {
        Order order = new Order();
        BeanUtils.copyProperties(params, order);
        order.setOrderTime(DateUtil.parserLocalDateTime(params.getOrderTime()));
        if (order.getOrderTime().isBefore(LocalDateTime.now())){
            throw new BusinessException(EResponseCode.BizError, "预约不能早于现在的时间", "");
        }
        order.setOrderId(UuidUtil.randomUUID());
        return orderMapper.insertSelective(order);
    }

    @Override
    public List<Order> selectByOrderDepartmentAndOrderDoctor(String orderDepartment,String orderDoctor) {
        return orderMapper.selectByOrderDepartmentAndOrderDoctor(orderDepartment,orderDoctor);
    }

    @Override
    public List<OrderListResult> getOrderList(OrderGetListParams params) {
        return orderMapper.getOrderList(params);
    }

    @Override
    public void updateOrderStateByOrderId(String updatedOrderState, String orderId) {
        orderMapper.updateOrderStateByOrderId(updatedOrderState, orderId);
    }

    @Override
    public OrderDetailResult getOrderDetail(String orderId) {
        return orderMapper.getOrderDetail(orderId);
    }

}
