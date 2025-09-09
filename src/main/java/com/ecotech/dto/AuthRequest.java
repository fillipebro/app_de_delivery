package com.ecotech.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthRequest {
    private String email;
    private String senha;
}
