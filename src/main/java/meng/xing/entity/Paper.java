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
    @ManyToMany(mappedBy = "papers",fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "paper_and_test_item",joinColumns = @JoinColumn(name = "test_item_id"),inverseJoinColumns = @JoinColumn(name = "paper_id"))
    private Set<TestItem>testItems;




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
