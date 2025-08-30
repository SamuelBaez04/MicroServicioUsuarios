package com.farmaclick.usuarios.models;

import java.util.UUID;


public class Usuario {
	
	private String id;
	private String nombre;
	private String apellido;
	private String email;
	private String cedula;
	private String password;
	private String rol;
	
	public Usuario() {
		this.id= UUID.randomUUID().toString();
	}
	
	public Usuario(String nombre, String apellido,String email, String cedula, String password, String rol) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.cedula = cedula;
		this.password = password;
		this.rol = rol;
	}
	

	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
