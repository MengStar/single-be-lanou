package meng.xing.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String answer;


    private Long userId;
    private long examId;

    private Long paperId;


    private Long testItemId;

    protected Answer() {
    }

    public Answer(String answer, Long userId, long examId, Long paperId, Long testItemId) {
        this.answer = answer;
        this.userId = userId;
        this.examId = examId;
        this.paperId = paperId;
        this.testItemId = testItemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Long getTestItemId() {
        return testItemId;
    }

    public void setTestItemId(Long testItemId) {
        this.testItemId = testItemId;
    }
}
