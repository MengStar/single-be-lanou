package meng.xing.api.normol.utils;

import java.util.HashMap;
import java.util.Map;

public class ReturnStatusFactory {
    public static Map<String, Object> create(boolean status, String message) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", status);
        ret.put("message", message);

        return ret;
    }
}
