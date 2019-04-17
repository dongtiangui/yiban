package com.yiban.yiban_application.common;
import com.yiban.yiban_application.javabean.Authorization;
import com.yiban.yiban_application.javabean.Role;
import com.yiban.yiban_application.javabean.Sys_admin;
import com.yiban.yiban_application.system.dao.AdminInterface;
import com.yiban.yiban_application.system.dao.AuthorizationInterface;
import com.yiban.yiban_application.system.dao.RoleInterface;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class jdbcRealm extends AuthorizingRealm {
    @Autowired
    private AdminInterface adminInterface;
    @Autowired
    private RoleInterface roleInterface;
    @Autowired
    private AuthorizationInterface authorizationInterface;
    @Override
    public String getName() {
        return "realm";
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Sys_admin user = (Sys_admin) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getAdmin_name();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = this.roleInterface.findUserRole(userName);
        Set<String> roleSet = new HashSet<String>();
        for (Role r : roleList) {
            roleSet.add(r.getRole_name());
        }
        simpleAuthorizationInfo.setRoles(roleSet);
        List<Authorization> permissionList = this.authorizationInterface.findUserPermissions(userName);
        Set<String> permissionSet = new HashSet<String>();
        for (Authorization m : permissionList) {
            permissionSet.add(m.getPerms());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) Token;
        String name =(String) token.getPrincipal();
        Sys_admin e = adminInterface.getAdmin(name);
        try {
            if (e.getAdmin_name()!=null&&e.getAdmin_pass()!=null){
                return new SimpleAuthenticationInfo(e.getAdmin_name(),e.getAdmin_pass(),getName());
            }
        }catch (Exception e1){
            e1.printStackTrace();
            throw new AuthenticationException();
        }
        return null;
    }
}
