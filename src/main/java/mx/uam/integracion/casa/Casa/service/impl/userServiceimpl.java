package mx.uam.integracion.casa.Casa.service.impl;

import mx.uam.integracion.casa.Casa.dto.UsuarioDTO;
import mx.uam.integracion.casa.Casa.entity.Usuario;
import mx.uam.integracion.casa.Casa.repository.UserRepository;
import mx.uam.integracion.casa.Casa.service.userService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userServiceimpl implements userService {
    private final UserRepository usuarioRepository;

    public userServiceimpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsById(usuarioDTO.getIdUsuario())) {
            throw new RuntimeException("Ya existe un usuario con el ID: " + usuarioDTO.getIdUsuario());
        }

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioDTO.getIdUsuario());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEdad(usuarioDTO.getEdad());

        Usuario savedUsuario = usuarioRepository.save(usuario);

        return convertToDTO(savedUsuario);
    }

    @Override
    public UsuarioDTO[] getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertToDTO)
                .toArray(UsuarioDTO[]::new);
    }

    @Override
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setEdad(usuarioDTO.getEdad());

            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return convertToDTO(updatedUsuario);
        } else {
            throw new RuntimeException("No se encontró un usuario con el ID: " + id);
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No se encontró un usuario con el ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEdad(usuario.getEdad());
        return usuarioDTO;
    }
}