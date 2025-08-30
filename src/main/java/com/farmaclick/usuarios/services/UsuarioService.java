package com.farmaclick.usuarios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmaclick.usuarios.models.Usuario;
import com.farmaclick.usuarios.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
}
