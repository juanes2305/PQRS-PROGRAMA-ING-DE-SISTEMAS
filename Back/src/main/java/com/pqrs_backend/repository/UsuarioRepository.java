package com.pqrs_backend.repository;

import com.pqrs_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(
            value = "SELECT * FROM usuario u where u.id_rol = :id_rol",
            nativeQuery=true
    )
    Usuario findByRol(int id_rol);

    @Query(
            value = "SELECT * FROM usuario u where u.email = :email",
            nativeQuery = true
    )
    Optional<Usuario> findByEmail(String email);
}
