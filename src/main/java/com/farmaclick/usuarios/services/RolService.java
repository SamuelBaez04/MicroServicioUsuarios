package com.farmaclick.usuarios.services;

import com.farmaclick.usuarios.models.Rol;
import com.farmaclick.usuarios.repositories.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private RolRepository rolRepository;

    public List<Rol> getAllRol(){
        return rolRepository.findAll();
    }

    public Optional<Rol> findRolById(Integer id){
        return rolRepository.findById(id);
    }

    public Rol createRol(Rol rol){
        if(rolRepository.existsByNombre(rol.getNombre())){
            throw new RuntimeException("Ya hay un rol con ese nombre");
        }
        return rolRepository.save(rol);
    }

    public boolean deleteRol(Integer id){
        if(rolRepository.existsById(id)){
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
