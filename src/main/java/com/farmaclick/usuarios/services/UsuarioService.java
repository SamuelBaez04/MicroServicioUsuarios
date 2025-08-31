package com.farmaclick.usuarios.services;

import java.util.List;
import java.util.Map;

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
		System.out.println("555555" + usuario.getNombre());

		return usuarioRepository.save(usuario);
	}
	
	public Usuario findById(String id) {
		return usuarioRepository.findById(id);
	}
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario update(Usuario usuario) {
		return usuarioRepository.update(usuario);
	}
	
	public Usuario patch(String id, Map<String, Object> updates) {
		Usuario usuario = usuarioRepository.findById(id);
		if(usuario != null) {
			updates.forEach((key, value) -> {
				switch (key) {
				case "nombre": 
					usuario.setNombre((String) value);
					break;
				case "apellido":
					usuario.setApellido((String) value);
					break;
				case "email":
					usuario.setEmail((String) value);
					break;
				case "cedula":
					usuario.setCedula((String) value);
					break;
				case "password":
					usuario.setPassword((String) value);
					break;
				}
			});
			return usuarioRepository.update(usuario);
		}
		return null;
	}
	
	public void deleteById(String id) {
		 usuarioRepository.deleteById(id);
	}
	
	
}
