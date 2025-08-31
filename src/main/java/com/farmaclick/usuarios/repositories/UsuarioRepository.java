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
	
	public Usuario findById(String id) {
		for (Usuario usuario : baseDeDatos) {
			if(usuario.getId().equals(id)) {
				return usuario;
			}
		}
		return null;
	}
	
	public List<Usuario> findAll(){
		return new ArrayList<>(baseDeDatos);
	}
	
	public Usuario update(Usuario usuario) {
		for(int i = 0; i < baseDeDatos.size(); i++) {
			if(baseDeDatos.get(i).getId().equals(usuario.getId())) {
				baseDeDatos.set(i, usuario);
				return usuario;
			}
		}
		return null;
	}
	
	public void deleteById(String id) {
		for(int i = 0; i < baseDeDatos.size(); i++) {
			if(baseDeDatos.get(i).getId().equals(id)) {
				baseDeDatos.remove(i);
				return;
			}
		}
	}
	
	
}
