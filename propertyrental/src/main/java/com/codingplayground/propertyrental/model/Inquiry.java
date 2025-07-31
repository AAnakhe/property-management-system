package com.codingplayground.propertyrental.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Property property;

    private String message;
    private String contactEmail;

}
