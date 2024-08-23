package com.example.apimypass.password;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_jad_mypass_password")
@Data
public class Password {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String url;
    String username;
    String password;


}
