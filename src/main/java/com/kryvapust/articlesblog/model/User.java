package com.kryvapust.articlesblog.model;

import com.kryvapust.articlesblog.model.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// заменить Data на другие
@Setter
@Getter
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date created;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public String getFullName() {
        return firstName + " " + lastName;
    }

//    @OneToMany(mappedBy = "articles", fetch = FetchType.LAZY)
//    List<Article> articles;
}

