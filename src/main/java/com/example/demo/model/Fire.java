package com.example.demo.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Fire {

    public Fire() {
        // Constructeur sans paramètre
    }

    public Fire(int severity, Instant date) {
        this.severity = severity;
        this.date = date;
    }

    // Getters et setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int severity;

    private Instant date;

    @ManyToOne
    @JoinColumn(name = "fireman_id")
    private Fireman fireman;

    // ...
}


