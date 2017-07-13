package meng.xing.security;

import meng.xing.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUserDetails create(User user) {
        return new JwtUserDetails(
                String.valueOf(user.getId()),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String authoritiesStr) {
        String[] authorities = authoritiesStr.split(",");
        List<String> list = new ArrayList<>();
        Collections.addAll(list,authorities);
        return list.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
