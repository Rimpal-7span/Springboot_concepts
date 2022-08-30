package com.example.restApi.model;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID uuid;
    private String name;
   // @ManyToMany(mappedBy = "websites")
    //private List<User> users;
}
