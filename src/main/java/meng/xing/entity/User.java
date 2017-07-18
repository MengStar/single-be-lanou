package meng.xing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import meng.xing.security.PasswordEncoderUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity //jpa的标签 根据字段，自动创表
@Table(name = "user") //生成的表名
@ToString(exclude = "password") //lombok标签, toString()忽略password
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//jpa 主键和自增
    @NotNull
    @Column(unique = true)
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


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_and_role", inverseJoinColumns = @JoinColumn(name = "role_id"), joinColumns = @JoinColumn(name = "user_id"))
    private Set<UserRole> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_exam", joinColumns = @JoinColumn(name = "use_id"), inverseJoinColumns = @JoinColumn(name = "exam_id"))
    private Set<Exam> exams;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_paper", joinColumns = @JoinColumn(name = "use_id"), inverseJoinColumns = @JoinColumn(name = "paper_id"))
    private Set<Paper> papers;

    protected User() {
    }

    /**
     * 新建User
     *
     * @param username 用户名
     * @param password 密码
     */
    public User(String username, String password) {
        this.username = username;
        this.password = PasswordEncoderUtil.passwordEncoding(password);
        Date date = new Date();
        this.createDate = date;
        this.lastPasswordResetDate = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }
}