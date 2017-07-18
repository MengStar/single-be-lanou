package meng.xing.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "test_item")
public class TestItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @ManyToMany(fetch = FetchType.LAZY ,mappedBy = "testItems")
    private Set<Paper> papers;

    private String question;
    private String answer;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
