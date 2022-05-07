package com.vtripathi.blogproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name", nullable = false)
    private String name;

    private String Email;

    private String Password;

    private String about;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> users = new ArrayList<>();

}
