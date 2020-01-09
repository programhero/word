package com.ye3z3.word;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_word")
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @ExcelProperty("单词")
    private String wordd;
    @Column(nullable = false)
    @ExcelProperty("类型")
    private String wordType;
    @Column
    @ExcelProperty("主题")
    private String theme;
    @Column(columnDefinition="int default 1")
    @ExcelProperty("频率")
    private Integer frequency;
    @Column
    @ExcelProperty("动词变位")
    @ColumnWidth(15)
    private String conjugation;
    @Column
    @ExcelProperty("固搭")
    private String phrase;
    @Column
    @ExcelProperty("示例")
    @ColumnWidth(50)
    private String example;
    @Column(length = 65535)
    @ExcelProperty("备注")
    @ColumnWidth(50)
    private String remark;
   @Column
    @ExcelProperty("创建时间")
   @ColumnWidth(20)
    private Date createdTime;
    @Column
    @ExcelProperty("最后更新时间")
    @ColumnWidth(20)
    private Date lastUpdatedTime;


}
