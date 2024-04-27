package com.henry.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;

@Data
@TableName("note")
public class Note {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String noteTitle;
    private String noteMd;
    private Integer userId;
    private Integer notebookId;
    private BigInteger noteTime;
}
