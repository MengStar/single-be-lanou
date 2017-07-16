package meng.xing.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/7/14.
 */
@Entity
@Data
@Table(name = "book_type")
public class BookType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String type; //类型名
    private String description;//类型描述
}
