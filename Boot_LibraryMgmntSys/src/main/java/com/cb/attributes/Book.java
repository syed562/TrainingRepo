package com.cb.attributes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private Long id;
    private String name;
    private String author;
    private Double cost;
    private int quantity;
    private Boolean isAvailable = true;
    private List<User> ownedBy = new ArrayList<>();

    public Book(Long id, String name, String author, Double cost, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.cost = cost;
        this.quantity = quantity;
        this.isAvailable = quantity > 0;
    }
}
