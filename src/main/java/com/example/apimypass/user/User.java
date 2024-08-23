package com.example.apimypass.user;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_jad_mypass_users")
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
}
