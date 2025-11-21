package com.cb.attributes;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private Long id;
    private String name;
    private String phnum;
    private Set<String> borrowedBooks = new HashSet<>();

    public User(Long id, String name, String phnum) {
        this.id = id;
        this.name = name;
        this.phnum = phnum;
    }
	
}
