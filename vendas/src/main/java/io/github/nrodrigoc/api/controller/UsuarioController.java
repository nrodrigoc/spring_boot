package io.github.nrodrigoc.api.controller;

import io.github.nrodrigoc.api.dto.UsuarioDTO;
import io.github.nrodrigoc.domain.model.Usuario;
import io.github.nrodrigoc.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    private final PasswordEncoder encoder;

    @PostMapping("/api/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid UsuarioDTO dto) {
        String senhaCriptografada = encoder.encode(dto.getSenha());
        dto.setSenha(senhaCriptografada);
        return usuarioService.salvar(dto);
    }

    @PostMapping("/api/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvarADM(@RequestBody @Valid UsuarioDTO dto) {
        String senhaCriptografada = encoder.encode(dto.getSenha());
        dto.setSenha(senhaCriptografada);
        return usuarioService.salvarADM(dto);
    }

    @GetMapping("/api/usuarios")
    public UsuarioDTO getById(@RequestParam Integer id) {
        return usuarioService.getById(id);
    }

}
