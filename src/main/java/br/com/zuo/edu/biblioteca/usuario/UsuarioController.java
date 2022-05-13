package br.com.zuo.edu.biblioteca.usuario;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(UsuarioController.BASE_URI)
public class UsuarioController {

    public final static String BASE_URI = "/usuarios";

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest,
                                       UriComponentsBuilder ucb) {
        Usuario usuario = usuarioRepository.save(usuarioRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
