package meng.xing.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "paper")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "paper_and_test_item", joinColumns = @JoinColumn(name = "test_item_id"), inverseJoinColumns = @JoinColumn(name = "paper_id"))
    private Set<TestItem> testItems;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paper")
    private Set<Exam> exams;


    protected Paper() {
    }

    public Paper(String description, Subject subject, User user, Set<TestItem> testItems) {
        this.description = description;
        this.subject = subject;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<TestItem> getTestItems() {
        return testItems;
    }

    public void setTestItems(Set<TestItem> testItems) {
        this.testItems = testItems;
    }
}
