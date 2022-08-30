package com.example.restApi.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Address extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID uuid;
    private int houseNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private int pincode;
//    @ManyToOne
//    @JoinColumn(name="user_id",referencedColumnName = "id")
//    private User user;


}
