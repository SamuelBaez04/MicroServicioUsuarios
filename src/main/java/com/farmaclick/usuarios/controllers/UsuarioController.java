package com.farmaclick.usuarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmaclick.usuarios.models.Usuario;
import com.farmaclick.usuarios.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/farmaclick/usuarios")
@Tag(name = "Usuarios", description = "Gestion de los Usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioservice) {
		this.usuarioService = usuarioservice;
	}
	
	@PostMapping
	@Operation(summary = "Creación de un nuevo Usuario", description = "Crea un nuevo usuario con los datos requeridos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Se creo exitosamente el usuario"),
			@ApiResponse(responseCode = "400", description = "Los datos ingresados son incorrectos")
	})	
	public ResponseEntity<Usuario> createUsuario(@RequestBody @Parameter(description = "Datos necesarios para crear el usuario") Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
	}
	
	@GetMapping
	@Operation(summary = "Obtiene todos los usuarios", description = "Retorna una lista, dentro de ella todos los usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Usuarios obtenida exitosamente"),
			@ApiResponse(responseCode = "500", description = "Error Interno")
	})
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> lista = usuarioService.findAll();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Consulta Usuario por id", description = "Retorna un usuario en especifico, basado en su id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Usuario no pudo ser encontrado con exito")
	})
	public ResponseEntity<Usuario> findById(@PathVariable @Parameter(description = "Id del usuario") String id){
		Usuario usuario = usuarioService.findById(id);
		if(usuario != null) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
