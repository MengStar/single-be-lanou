package meng.xing.service.auth;

import meng.xing.entity.User;

/**
 * Created by Administrator on 2017/7/13.
 */
public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);

}
