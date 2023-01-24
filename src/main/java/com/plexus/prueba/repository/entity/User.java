package com.plexus.prueba.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User {

    @Id
    private Integer id;

    private String username;

    private String password;

}
