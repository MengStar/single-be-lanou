package meng.xing.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import meng.xing.security.PasswordEncoderUtil;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity //jpa的标签 根据字段，自动创表
@Table(name = "user") //生成的表名
@Data //lombok标签，自动生成getter、setter、toString，需要编辑器支持
@ToString(exclude = "password") //lombok标签, toString()忽略password
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//jpa 主键和自增
    @NotNull
    private String username;
    @NotNull
    @JsonIgnore
    private String password; //转json 忽略password
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastPasswordResetDate;
    @NotNull
    private String rolesStr;

    protected User() {
    }

    /**
     * 新建User
     * @param username 用户名
     * @param password 密码
     * @param roles    权限 ，可多个
     */
    public User(String username, String password, String... roles) {
        this.username = username;
        this.password = PasswordEncoderUtil.passwordEncoding(password);
        String rolesStr = "";
        int len = roles.length;
        for (int i = 0; i < len; i++) {
            if (i < len - 1) {
                rolesStr += roles[i] + "|";
            } else {
                rolesStr += roles[i];
            }
        }
        this.rolesStr = rolesStr;
        Date date = new Date();
        this.createDate = date;
        this.lastPasswordResetDate = date;
    }

    /**
     * 将权限字符串解析为String[]
     * @return
     */
    public String[] getRoles() {
        String[] roles = rolesStr.split("\\|");
        return roles;
    }

}