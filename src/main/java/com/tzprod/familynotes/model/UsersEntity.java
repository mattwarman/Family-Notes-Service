package com.tzprod.familynotes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "users", schema = "familynotes")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iduser", nullable = false)
    private int iduser;
    @Basic
    @Column(name = "firstname", nullable = false, length = 45)
    private String firstname;
    @Basic
    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;
    @Basic
    @Column(name = "alias", nullable = false, length = 25)
    private String alias;
    @Basic
    @Column(name = "password", nullable = false, length = 25)
    private String password;
    @Basic
    @Column(name = "time", nullable = true)
    private LocalDateTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return iduser == that.iduser && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(alias, that.alias) && Objects.equals(password, that.password) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, firstname, lastname, alias, password, time);
    }
}
