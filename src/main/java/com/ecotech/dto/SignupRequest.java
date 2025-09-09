package com.ecotech.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SignupRequest {
    private String nome;
    private String email;
    private String senha;
    private String endereco;
}
