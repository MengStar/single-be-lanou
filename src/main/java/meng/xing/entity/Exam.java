package meng.xing.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exam_and_paper", joinColumns = @JoinColumn(name = "exam_id"), inverseJoinColumns = @JoinColumn(name = "paper_id"))
    private Set<Paper> papers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "exam_and_user", joinColumns = @JoinColumn(name = "exam_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;


    public Exam() {

    }

    public Exam(String description, Subject subject, Set<Paper> papers, Set<User> users) {
        this.description = description;
        this.subject = subject;
        this.papers = papers;
        this.users = users;
    }

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
