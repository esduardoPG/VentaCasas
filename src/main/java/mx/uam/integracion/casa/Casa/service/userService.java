package mx.uam.integracion.casa.Casa.service;

import mx.uam.integracion.casa.Casa.dto.UsuarioDTO;

public interface userService {
    UsuarioDTO createUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO[] getAllUsuarios();
    UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO);
    void deleteUsuario(Long id);
}