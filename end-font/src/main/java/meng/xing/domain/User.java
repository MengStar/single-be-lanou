package meng.xing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import meng.xing.security.PasswordEncoderUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

//jpa的标签
@Entity
//生成的表名
@Table(name = "user")
@Data
@ToString(exclude = "password")
public class User {
    //主键和自增
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //jsr303的验证
    @NotNull
    private String username;
    @NotNull
    @JsonIgnore
    private String password;
    @NotNull
    private Date createDate;
    @NotNull
    private Date lastPasswordResetDate;
    @NotNull
    private String[] roles;

    protected User() {
    }

    public User(String username, String password, String... roles) {
        this.username = username;
        this.password = PasswordEncoderUtil.passwordEncoding(password);
        this.roles = roles;
        Date date = new Date();
        this.createDate = date;
        this.lastPasswordResetDate=date;
    }

}