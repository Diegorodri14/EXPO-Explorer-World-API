package com.example.Api_Expo_2025.Services;

import com.example.Api_Expo_2025.Models.ClienteDTO;
import com.example.Api_Expo_2025.Entities.EntityCliente;
import com.example.Api_Expo_2025.Repositories.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServices {

    @Autowired
    private RepositoryCliente repo;

    public List<ClienteDTO> getAllClientes(){
        List<EntityCliente> clientes = repo.findAll();
        return clientes.stream()
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO convertirAClienteDTO(EntityCliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getId());
        dto.setNombreCliente(cliente.getNombre());
        dto.setApellidoCliente(cliente.getApellido());
        dto.setEmailCliente(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setDui(cliente.getDui());
        return dto;
    }
}