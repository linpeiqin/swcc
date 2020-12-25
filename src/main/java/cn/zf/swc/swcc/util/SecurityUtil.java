package cn.zf.swc.swcc.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Spring Security工具类
 */
public class SecurityUtil {
    /**
     * 从ThreadLocal获取其自己的SecurityContext，从而获取在Security上下文中缓存的登录用户
     */
    public static User getLoginUser() {
        User user = null;
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) user = (User) auth.getPrincipal();
        assert user != null;
        return user;
    }

    public static Boolean hasAuthentication(String auth){
        Collection<? extends GrantedAuthority> authorities = SecurityUtil.getLoginUser().getAuthorities();
        for (GrantedAuthority authoritie : authorities){
            if (authoritie.getAuthority().equals(auth)){
                return true;
            }
        }
        return false;
    }
}
