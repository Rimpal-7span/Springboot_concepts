package com.example.restApi.model;


import com.example.restApi.enumclass.Gender;
import com.example.restApi.enumclass.UserType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE User SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class User extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "binary(16)")
    private UUID uuid;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;
    private boolean deleted = Boolean.FALSE;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private List<Address> address;
   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_websites",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    private List<Website> websites;*/
   // @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@JoinColumn(name= "user_id", referencedColumnName = "id")
  //  private Credential credential;

}

