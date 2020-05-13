package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.entity.Doctor;
import com.usongon.pocketmedical.bean.param.OrderGetListParams;
import com.usongon.pocketmedical.bean.param.OrderInsertParams;
import com.usongon.pocketmedical.bean.param.PatientSelectParams;
import com.usongon.pocketmedical.bean.result.DoctorResult;
import com.usongon.pocketmedical.bean.result.OrderDetailResult;
import com.usongon.pocketmedical.bean.session.AdminSession;
import com.usongon.pocketmedical.bean.session.DoctorSession;
import com.usongon.pocketmedical.bean.session.PatientSession;
import com.usongon.pocketmedical.common.helper.GlobalHelper;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.service.DoctorService;
import com.usongon.pocketmedical.service.OrderService;
import com.usongon.pocketmedical.service.PatientService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020/5/13
 */
@RestController
@Authorize
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private PatientService patientService;
    @Resource
    private DoctorService doctorService;

    @PostMapping("/patient/order/add")
    public Object addOrder(OrderInsertParams params){
        PatientSession session = GlobalHelper.get();
        PatientSelectParams patientSelectParams = new PatientSelectParams();
        patientSelectParams.setPatientId(session.getPatientId());
        if (patientService.selectByAllExceptId(patientSelectParams) == null){
            throw new BusinessException(EResponseCode.BizError, "只有患者可以发帖", "");
        }
        params.setOrderPatient(session.getPatientId());
        orderService.insertSelective(params);
        return ResponseResult.success();
    }

    /**
     * 患者查看自己发布的预约
     */
    @PostMapping("/patient/order/list")
    public Object patientGetOrderList(OrderGetListParams params){
        PatientSession session = GlobalHelper.get();
        params.setOrderPatient(session.getPatientId());
        return ResponseResult.success(orderService.getOrderList(params));
    }

    /**
     * 医生查看和自己相关的帖子
     */
    @PostMapping("/doctor/order/list")
    public Object doctorGetOrderList(OrderGetListParams params){
        DoctorSession session = GlobalHelper.get();
        params.setOrderDepartment(doctorService.selectByDocIdAndDocState(session.getDoctorId()).getDepartmentId());
        params.setOrderDoctor(session.getDoctorId());
        return ResponseResult.success(orderService.getOrderList(params));
    }

    /**
     * 管理员查看所有帖子
     */
    @PostMapping("/admin/order/list")
    public Object adminGetOrderList(OrderGetListParams params){
        AdminSession session = GlobalHelper.get();
        return ResponseResult.success(orderService.getOrderList(params));
    }

    /**
     * 本人查看预约详情
     */
    @PostMapping("/patient/order/detail")
    public Object patientGetOrderDetail(String orderId){
        PatientSession session = GlobalHelper.get();
        OrderDetailResult result = orderService.getOrderDetail(orderId);
        if (!result.getOrderPatient().equals(session.getPatientId())){
            throw new BusinessException(EResponseCode.BizError, "你不能查看其他人的预约", "");
        }
        return ResponseResult.success(result);
    }

    /**
     * 医生查看自己下面的预约详情
     */
    @PostMapping("/doctor/order/detail")
    public Object doctorGetOrderDetail(String orderId){
        DoctorSession session = GlobalHelper.get();
        OrderDetailResult orderResult = orderService.getOrderDetail(orderId);
        if (!StringUtils.isEmpty(orderResult.getOrderDoctor()) && !orderResult.getOrderDoctor().equals(session.getDoctorId())){
            throw new BusinessException(EResponseCode.BizError, "这不是与您有关的预约", "");
        }
        Doctor doctorResult = doctorService.selectByDocIdAndDocState(session.getDoctorId());
        if (!orderResult.getOrderDepartment().equals(doctorResult.getDepartmentId())){
            throw new BusinessException(EResponseCode.BizError, "这不是与您的部门有关的预约", "");
        }
        return ResponseResult.success(orderResult);
    }

    /**
     * 超管查看预约详情
     */
    @PostMapping("/admin/order/detail")
    public Object adminGetOrderDetail(String orderId){
        AdminSession session = GlobalHelper.get();
        return ResponseResult.success(orderService.getOrderDetail(orderId));
    }

    /**
     * 患者删除或者完结自己的预约
     */
    @PostMapping("/patient/order/state")
    public Object patientOrderState(String orderId, String state){
        PatientSession session = GlobalHelper.get();
        OrderDetailResult result = orderService.getOrderDetail(orderId);
        if (!result.getOrderPatient().equals(session.getPatientId())){
            throw new BusinessException(EResponseCode.BizError, "你不能修改其他人的预约", "");
        }
        orderService.updateOrderStateByOrderId(state, orderId);
        return ResponseResult.success();
    }

    /**
     * 医生接受/拒绝/完结 预约
     */

    @PostMapping("/doctor/order/state")
    public Object doctorOrderState(String orderId, String state){
        DoctorSession session = GlobalHelper.get();
        OrderDetailResult orderResult = orderService.getOrderDetail(orderId);
        if (!StringUtils.isEmpty(orderResult.getOrderDoctor()) && !orderResult.getOrderDoctor().equals(session.getDoctorId())){
            throw new BusinessException(EResponseCode.BizError, "这不是与您有关的预约", "");
        }
        Doctor doctorResult = doctorService.selectByDocIdAndDocState(session.getDoctorId());
        if (!orderResult.getOrderDepartment().equals(doctorResult.getDepartmentId())){
            throw new BusinessException(EResponseCode.BizError, "这不是与您的部门有关的预约", "");
        }
        orderService.updateOrderStateByOrderId(state, orderId);
        return ResponseResult.success();
    }

    /**
     * 超管 完结/删除 预约
     */

    @PostMapping("/admin/order/state")
    public Object adminOrderState(String orderId, String state){
        AdminSession session = GlobalHelper.get();
        orderService.updateOrderStateByOrderId(state, orderId);
        return ResponseResult.success();
    }
}
