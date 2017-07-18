package meng.xing.entity;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exam_and_paper")
    private Set<Paper> papers;

    @ManyToMany(mappedBy = "exams",fetch = FetchType.LAZY)
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
