package meng.xing.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

//jpa的标签
@Entity
//生成的表名
@Table(name = "my_user")
public class User {
    //主键和自增
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    //jsr303的验证
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private int role;
    @NotNull
    private Date createdate;
    public User() {}



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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}