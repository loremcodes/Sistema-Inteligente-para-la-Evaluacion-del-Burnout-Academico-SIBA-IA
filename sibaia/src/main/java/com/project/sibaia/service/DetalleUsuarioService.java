package com.project.sibaia.service;

import com.project.sibaia.entity.Administrador;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetalleUsuarioService implements UserDetailsService {

    private final AdministradorService administradorService;

    public DetalleUsuarioService(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrador admin = administradorService.buscarPorUsuario(username);

        if (admin == null) {
            throw new UsernameNotFoundException("El administrador con el usuario '" + username + "' no existe.");
        }

        return User.builder()
                .username(admin.getUsuario())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }
}