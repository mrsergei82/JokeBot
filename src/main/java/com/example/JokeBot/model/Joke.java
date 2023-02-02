package com.example.JokeBot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Joke {
    private String body;
    private String category;

    @Id
    private Integer id;
    private double rating;
}
