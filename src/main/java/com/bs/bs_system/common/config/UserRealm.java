package com.bs.bs_system.common.config;

import com.bs.bs_system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行授权-----");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermission("");
        Subject subject = SecurityUtils.getSubject();
        SysUser currentUser = (SysUser) subject.getPrincipal();     //拿到user对象
        authorizationInfo.addStringPermission("");
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行认证-----");

        String username = "admin";
        String password = "123456";

        SysUser sysUser = new SysUser();
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        if (!userToken.getUsername().equals(username)) {
            return null;//抛出异常，返回用户名错误
        }

        //密码认证
        return new SimpleAuthenticationInfo("", password, "");
    }
}
