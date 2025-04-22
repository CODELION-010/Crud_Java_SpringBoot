package com.registro2.crud.controller;

import com.registro2.crud.model.Usuario;
import com.registro2.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioWebController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar lista de usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    // Mostrar formulario de nuevo usuario
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formulario_usuario";
    }

    // Guardar nuevo usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    // Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "formulario_usuario";
    }

    // Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}
