package com.project.soruCevap.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Post")
public class Post {

    @Id
    private String id;

    private String postTitle;
    private String postSefLink;
    private String postContent;

    private String userId;
    private String categoryId;

    private Integer postApprovalStatus;

    private Date postCreateTime;

}
