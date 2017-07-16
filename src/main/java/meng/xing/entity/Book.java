package meng.xing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@NotNull
    // TODO: 2017/7/14
    private long[] bokTypeIds; //类别
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

    private Book() {
    }

    public Book(String bookName, String authorName, String bookPress, Date pubDate, String bookAbstract) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPress = bookPress;
        this.pubDate = pubDate;
        this.bookAbstract = bookAbstract;
    }

}
