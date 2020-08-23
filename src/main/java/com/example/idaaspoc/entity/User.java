package com.example.idaaspoc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private boolean enabled;
    @Column
    private String technicalId;
    @OneToMany(mappedBy = "user")
    List<UserRoles> userRoles = new ArrayList<>();
}
