package meng.xing.domain;

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
    @Id @GeneratedValue(strategy = GenerationType.AUTO) //jpa 主键和自增
    private Long id;
    @NotNull  //jsr303的验证
    private String username;
    @NotNull
    @JsonIgnore //转json 忽略password
    private String password;
    @NotNull
    private Date createDate;
    @NotNull
    private Date lastPasswordResetDate;
    @NotNull
    private String[] roles;
    protected User() {
    }

    /**
     * 新建User
     * @param username 用户名
     * @param password 密码
     * @param roles 权限 ，可多个
     */
    public User(String username, String password, String... roles) {
        this.username = username;
        this.password = PasswordEncoderUtil.passwordEncoding(password);
        this.roles = roles;
        Date date = new Date();
        this.createDate = date;
        this.lastPasswordResetDate=date;
    }

}