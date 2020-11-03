package io.github.nrodrigoc.api.controller;

import io.github.nrodrigoc.api.dto.CredenciaisDTO;
import io.github.nrodrigoc.api.dto.TokenDTO;
import io.github.nrodrigoc.api.dto.UsuarioDTO;
import io.github.nrodrigoc.domain.model.Usuario;
import io.github.nrodrigoc.exception.SenhaInvalidaException;
import io.github.nrodrigoc.security.JwtService;
import io.github.nrodrigoc.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UsuarioServiceImpl usuarioService;

    @Autowired
    private final PasswordEncoder encoder;

    @PostMapping("/api/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario dto) {
        String senhaCriptografada = encoder.encode(dto.getSenha());
        dto.setSenha(senhaCriptografada);
        return usuarioService.salvar(dto);
    }

//    @PostMapping("/api/admin")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Usuario salvarADM(@RequestBody @Valid UsuarioDTO dto) {
//        String senhaCriptografada = encoder.encode(dto.getSenha());
//        dto.setSenha(senhaCriptografada);
//        return usuarioService.salvarADM(dto);
//    }

//    @GetMapping("/api/usuarios")
//    public UsuarioDTO getById(@RequestParam Integer id) {
//        return usuarioService.getById(id);
//    }

    @PostMapping("/api/usuarios/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
