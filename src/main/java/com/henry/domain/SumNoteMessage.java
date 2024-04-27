package com.henry.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class SumNoteMessage {
  private Integer noteId;
  private String noteTitle;
  private BigInteger noteTime;
  private String noteMd;
  private String nickName;
  private String imageUrl;
  private String notebookName;
  private String notebookImageUrl;
}
