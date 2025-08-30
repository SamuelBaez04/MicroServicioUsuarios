package com.farmaclick.usuarios.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.farmaclick.usuarios.models.Usuario;

@Repository
public class UsuarioRepository {
	
	private final List<Usuario> baseDeDatos = new ArrayList<>();
	
	public Usuario save(Usuario usuario) {
		baseDeDatos.add(usuario);
		return usuario;
	}
	
	
	
	
}
