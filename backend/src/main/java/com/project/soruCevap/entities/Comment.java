package com.project.soruCevap.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "Comment")
public class Comment {

    @Id
    private String id;

    //@NotNull
    private String commentContent;

    private String userId;
    private String postId;
    private Integer commentApprovalStatus;
    private Integer expertAnswer;
    private String commentCreateTime;
}
