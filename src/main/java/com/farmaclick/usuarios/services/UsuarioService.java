package com.farmaclick.usuarios.services;

import com.farmaclick.usuarios.dto.LoginRequestDTO;
import com.farmaclick.usuarios.dto.LoginResponseDTO;
import com.farmaclick.usuarios.dto.UsuarioCreateDTO;
import com.farmaclick.usuarios.dto.UsuarioDTO;
import com.farmaclick.usuarios.jwt.JwtService;
import com.farmaclick.usuarios.models.Rol;
import com.farmaclick.usuarios.models.Usuario;
import com.farmaclick.usuarios.repositories.RolRepository;
import com.farmaclick.usuarios.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // ======================================================
    //  CONVERSIÓN DE ENTIDAD → DTO
    // ======================================================
    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setDireccion(usuario.getDireccion());
        dto.setNombreRol(usuario.getRol().getNombre());
        return dto;
    }


    // ======================================================
    //  REGISTRO / CREAR USUARIO
    // ======================================================
    public UsuarioDTO create(UsuarioCreateDTO dto) {

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está registrado.");
        }

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("El rol no existe"));

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(rol);

        Usuario guardado = usuarioRepository.save(usuario);

        return toDTO(guardado);
    }


    // ======================================================
    //  LISTAR TODOS LOS USUARIOS
    // ======================================================
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    // ======================================================
    //  OBTENER POR ID
    // ======================================================
    public Optional<UsuarioDTO> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id)
                .map(this::toDTO);
    }


    // ======================================================
    //  ACTUALIZAR USUARIO
    // ======================================================
    public Optional<UsuarioDTO> updateUsuario(Integer id, UsuarioCreateDTO dto) {

        return usuarioRepository.findById(id).map(usuario -> {

            usuario.setNombre(dto.getNombre());
            usuario.setApellido(dto.getApellido());
            usuario.setEmail(dto.getEmail());
            usuario.setTelefono(dto.getTelefono());
            usuario.setDireccion(dto.getDireccion());

            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
            }

            Rol rol = rolRepository.findById(dto.getIdRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            usuario.setRol(rol);

            Usuario actualizado = usuarioRepository.save(usuario);

            return toDTO(actualizado);
        });
    }


    // ======================================================
    //  ELIMINAR USUARIO
    // ======================================================
    public boolean deleteUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Usuario> findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioDTO register(UsuarioCreateDTO dto) {

        // Verificar si el correo ya existe
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Buscar el rol CLIENTE (id = 2)
        Rol rolCliente = rolRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("El rol CLIENTE no existe"));

        // Crear entidad usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setEmail(dto.getEmail());

        // Encriptar password
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Asignar rol
        usuario.setRol(rolCliente);

        // Guardar
        Usuario saved = usuarioRepository.save(usuario);

        // Devolver DTO
        return toDTO(saved);
    }

    public LoginResponseDTO login(LoginRequestDTO dto) throws AuthenticationException {

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
        String token = jwtService.generateToken(userDetails);
        return new LoginResponseDTO(token);
    }


}
