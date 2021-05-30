package br.com.netprecision.configapi.model;

import br.com.netprecision.configapi.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role")
@SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "role_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
