package br.com.netprecision.configapi.repository;

import br.com.netprecision.configapi.constant.ERole;
import br.com.netprecision.configapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
