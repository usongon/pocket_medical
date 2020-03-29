package com.usongon.pocketmedical.framework;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler
    public Object processException(Exception e) {
        if (e instanceof BusinessException) {
            return new ResponseResult(((BusinessException) e).getCode().getCode(),
                    ((BusinessException) e).getMsg(), ((BusinessException) e).getData());
        }

        // 这里可能有点问题，会把错误信息暴露前端
        log.error(e.getMessage(), e);
        return new ResponseResult(EResponseCode.SysError.getCode(), "系统错误", "");
    }
}
