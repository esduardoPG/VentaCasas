package mx.uam.integracion.casa.Casa.service;

import mx.uam.integracion.casa.Casa.dto.UserDTO;

public interface userService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO[] getAllUsers();
    void deleteUser(String id);
    UserDTO updateUser(String id, UserDTO userDTO);
}