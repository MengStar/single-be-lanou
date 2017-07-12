package meng.xing.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

//jpa的标签
@Entity
//生成的表名
@Table(name = "user")
public class User {
    //主键和自增
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //jsr303的验证
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Date createDate;
    @NotNull
    private String role;

    public User() {
    }






    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private Date lastPasswordResetDate;


    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}