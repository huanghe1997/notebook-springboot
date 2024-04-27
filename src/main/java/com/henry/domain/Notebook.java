package com.henry.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;

@Data
@TableName("notebook")
public class Notebook {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private  String notebookName;
    private  String notebookImageUrl;
    private String userId;
    private BigInteger notebookTime;
    @TableField(exist = false)
    private Integer notebookCount;
}
