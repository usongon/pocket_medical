package com.usongon.pocketmedical.framework.interceptor;
import com.usongon.pocketmedical.bean.session.AdminSession;
import com.usongon.pocketmedical.bean.session.LoginSession;
import com.usongon.pocketmedical.bean.session.UserSession;
import com.usongon.pocketmedical.common.helper.GlobalHelper;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.redis.SessionRedis;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private SessionRedis sessionRedis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 有注解标明不需要登录则直接放行
        Authorize authorizeAnnotation = getAuthorizeByHandler(handler);
        if (authorizeAnnotation == null || !authorizeAnnotation.login()) {
            return true;
        }
        doCheckLogin(request.getRequestURI(), request.getHeader("token"));
        return true;
    }

    /**
     * 检查登录状态和CSRF
     */
    private void doCheckLogin(String uri, String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(EResponseCode.NoLogin, "未登录", "");
        }
        LoginSession session = sessionRedis.getSession(token);
        if (session != null) {
            if (uri.startsWith("/admin/") && !(session instanceof AdminSession)) {
                throw new BusinessException(EResponseCode.PermissionDenied, "无权限", "");
            }
            if (uri.startsWith("/user/") && !(session instanceof UserSession)) {
                throw new BusinessException(EResponseCode.PermissionDenied, "无权限", "");
            }
            sessionRedis.setExpire(token);
            GlobalHelper.set(session);
        } else {
            throw new BusinessException(EResponseCode.NoLogin, "未登录", "");
        }
    }

    /**
     * 从handler中获取注解
     * @param handler
     * @return
     */
    private Authorize getAuthorizeByHandler(Object handler) {
        // 取出注解
        HandlerMethod method = (HandlerMethod) handler;
        Authorize authorizeAnnotation = method.getMethodAnnotation(Authorize.class);
        if (authorizeAnnotation == null) {
            authorizeAnnotation = method.getMethod().getDeclaringClass().getAnnotation(Authorize.class);
        }
        return authorizeAnnotation;
    }
}
