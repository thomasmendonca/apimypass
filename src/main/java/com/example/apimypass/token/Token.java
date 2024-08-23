package com.example.apimypass.token;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "tb_jad_mypass_token")
@Data
public class Token {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String token;
    String type;
    String username;



}
