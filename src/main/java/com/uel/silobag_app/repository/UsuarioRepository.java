package com.uel.silobag_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uel.silobag_app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailAndSenha(String email, String senha);
}
