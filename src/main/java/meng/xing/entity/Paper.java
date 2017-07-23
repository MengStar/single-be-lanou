package meng.xing.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "paper")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String  description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "paper_and_user", joinColumns = @JoinColumn(name = "paper_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "paper_and_test_item",joinColumns = @JoinColumn(name = "test_item_id"),inverseJoinColumns = @JoinColumn(name = "paper_id"))
    private Set<TestItem>testItems;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "papers")
    private  Set<Exam> exams;

    public Paper(){}

    public Paper(String description, Subject subject, Set<User> users, Set<TestItem> testItems) {
        this.description = description;
        this.subject = subject;
        this.users = users;
        this.testItems = testItems;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
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
        this.subject = this.subject;
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

    public Set<TestItem> getTestItems() {
        return testItems;
    }

    public void setTestItems(Set<TestItem> testItems) {
        this.testItems = testItems;
    }
}
