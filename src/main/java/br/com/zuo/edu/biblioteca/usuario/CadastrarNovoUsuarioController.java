package br.com.zuo.edu.biblioteca.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoUsuarioController {

    private final UsuarioRepository usuarioRepository;

    public CadastrarNovoUsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarNovoUsuarioRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = request.toModel();

        usuarioRepository.save(usuario);

        URI location = uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
