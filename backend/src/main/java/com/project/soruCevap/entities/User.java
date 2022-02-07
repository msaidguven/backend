package com.project.soruCevap.entities;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "User")
public class User {
    @Id
    private String id;

    @NotNull//(message = "User's first name must not be null")
    private String firstName;

    @NotNull//(message = "User's last name must not be null")
    private String lastName;

    @Indexed(unique=true, sparse=true)
    private String email;

    private String password;
    private Date userCreateTime;
}
