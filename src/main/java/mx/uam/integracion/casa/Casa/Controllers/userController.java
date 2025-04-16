package mx.uam.integracion.casa.Casa.Controllers;

import mx.uam.integracion.casa.Casa.dto.UsuarioDTO;
import mx.uam.integracion.casa.Casa.service.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class userController {
    private final userService usuarioService;

    public userController(userService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/user")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioService.createUsuario(usuarioDTO);
        return ResponseEntity.ok(createdUsuario);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        List<UsuarioDTO> usuarios = List.of(usuarioService.getAllUsuarios());
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuario = usuarioService.updateUsuario(id, usuarioDTO);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}