package com.farmaclick.usuarios.controllers;

import java.util.List;
import java.util.Optional;

import com.farmaclick.usuarios.dto.LoginRequestDTO;
import com.farmaclick.usuarios.dto.LoginResponseDTO;
import com.farmaclick.usuarios.dto.UsuarioCreateDTO;
import com.farmaclick.usuarios.dto.UsuarioDTO;

import com.farmaclick.usuarios.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.farmaclick.usuarios.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/farmasync/usuarios")
@Tag(name = "Usuarios", description = "Gestión de Usuarios en la farmacia")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    // ======================================================
    //  CREAR (USANDO DTO)
    // ======================================================
    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<UsuarioDTO> createUsuario(
            @RequestBody @Parameter(description = "Datos para registrar el usuario") UsuarioCreateDTO dto) {

        UsuarioDTO nuevoUsuario = usuarioService.create(dto);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }


    // ======================================================
    //  LISTAR
    // ======================================================
    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista con todos los usuarios")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }


    // ======================================================
    //  OBTENER POR ID
    // ======================================================
    @GetMapping("/{id}")
    @Operation(summary = "Consultar usuario por ID", description = "Retorna un usuario correspondiente al ID ingresado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<UsuarioDTO> findById(
            @PathVariable @Parameter(description = "ID del usuario") Integer id) {

        Optional<UsuarioDTO> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    // ======================================================
    //  ACTUALIZAR (PUT)
    // ======================================================
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza un usuario existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<UsuarioDTO> updateUsuario(
            @PathVariable @Parameter(description = "ID del usuario a actualizar") Integer id,
            @RequestBody @Parameter(description = "Datos nuevos") UsuarioCreateDTO dto) {

        return usuarioService.updateUsuario(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // ======================================================
    //  ELIMINAR
    // ======================================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario de la base de datos por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Void> deleteUsuario(
            @PathVariable @Parameter(description = "ID del usuario a eliminar") Integer id) {

        if (usuarioService.deleteUsuario(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        try{
            LoginResponseDTO responseDTO = usuarioService.login(dto);
            return ResponseEntity.ok(responseDTO);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error credenciales erroneas");
        }
    }



}
