package br.com.netprecision.configapi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SignupRequest {
    @NotEmpty
    @Size(min = 3)
    private String username;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8)
    private String password;
}
