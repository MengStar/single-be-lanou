package meng.xing.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull private long[] bokTypeIds; //类别
    @NotNull private String callNumber;//索书号
    @NotNull private String bookName;//书名
    @NotNull private String authorName;//作者
    @NotNull private String bookPress;//出版社
    @NotNull private Date pubDate;//出版时间
    @NotNull private String bookAbstract;//摘要
    @NotNull private Date storeTime;//入库时间
    private Book(){}
    public Book(String bookName,String authorName,String bookPress,Date pubDate,String bookAbstract){
        this.bookName=bookName;
        this.authorName=authorName;
        this.bookPress=bookPress;
        this.pubDate=pubDate;
        this.bookAbstract=bookAbstract;
    }

}
