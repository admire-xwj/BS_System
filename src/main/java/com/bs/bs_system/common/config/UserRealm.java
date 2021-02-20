package com.bs.bs_system.common.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.bs_system.entity.SysUser;
import com.bs.bs_system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;

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
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.lambda().eq(SysUser::getLoginName, userToken.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(qw);
        if (ObjectUtils.isEmpty(sysUser)) {
            return null;//抛出异常
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("userName", sysUser.getLoginName());
        session.setAttribute("password", sysUser.getPassword());
        //密码认证
        return new SimpleAuthenticationInfo("", sysUser.getPassword(), "");
    }
}
