package com.ecotech.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private String token;
    private String nome;
    private String email;
}
