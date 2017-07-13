package meng.xing.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2017/7/13.
 */
@Component
public class JwtTokenUtil {

    public String getUsernameFromToken(String authToken) {
        return "dave0";
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        return true;
    }
}
