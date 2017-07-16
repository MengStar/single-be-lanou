package meng.xing.security;

import meng.xing.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * JwtUserDetails的工厂类
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }
    public static JwtUserDetails create(User user) {
        return new JwtUserDetails(
                String.valueOf(user.getId()),
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

}
