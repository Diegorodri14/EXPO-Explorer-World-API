package com.example.Api_Expo_2025.Controllers;

import com.example.Api_Expo_2025.Models.ClienteDTO;
import com.example.Api_Expo_2025.Services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiClientes")
public class ClienteControllers {

    @Autowired
    private ClienteServices service;

    @GetMapping("/getAll")
    public List<ClienteDTO> getAllClientes(){
        return service.getAllClientes();
    }
}