package br.com.netprecision.configapi.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JwtResponse {
    private String jwt;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
