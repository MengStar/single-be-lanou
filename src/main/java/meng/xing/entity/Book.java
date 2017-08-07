package meng.xing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String callNumber;//索书号
    @NotNull
    private String bookName;//书名
    @NotNull
    private String authorName;//作者
    @NotNull
    private String bookPress;//出版社
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;//出版时间
    @NotNull
    private String bookAbstract;//摘要
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date storeTime;//入库时间

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_and__type", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<BookType> bookTypes;

    protected Book() {
    }

    public Book(String bookName, String authorName, String bookPress, Date pubDate, String bookAbstract) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPress = bookPress;
        this.pubDate = pubDate;
        this.bookAbstract = bookAbstract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getBookAbstract() {
        return bookAbstract;
    }

    public void setBookAbstract(String bookAbstract) {
        this.bookAbstract = bookAbstract;
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }

    public Set<BookType> getBookTypes() {
        return bookTypes;
    }

    public void setBookTypes(Set<BookType> bookTypes) {
        this.bookTypes = bookTypes;
    }
}
