package meng.xing.security;

import meng.xing.entity.User;
import meng.xing.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<UserRole> authorities) {

        return
                authorities == null ? AuthorityUtils.NO_AUTHORITIES:
                authorities.stream().map(role -> role.getRole())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


}
