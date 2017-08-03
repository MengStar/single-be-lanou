package meng.xing.entity;

import lombok.ToString;

import javax.persistence.*;

@Entity //jpa的标签 根据字段，自动创表
@Table(name = "user_done_exam") //生成的表名
@ToString(exclude = "password") //lombok标签, toString()忽略password
public class UserDoneExam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//jpa 主键和自增

    @ManyToOne
    private Exam exam;
    @ManyToOne
    private User user;

    protected UserDoneExam() {
    }

    public UserDoneExam(Exam exam, User user) {
        this.exam = exam;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
