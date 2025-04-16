package mx.uam.integracion.casa.Casa.service.impl;

import mx.uam.integracion.casa.Casa.dto.UserDTO;
import mx.uam.integracion.casa.Casa.entity.User;
import mx.uam.integracion.casa.Casa.repository.UserRepository;
import mx.uam.integracion.casa.Casa.service.userService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userServiceimpl implements userService {
    private final UserRepository userRepository;

    public userServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Crear una nueva entidad User a partir del DTO
        User user = new User();
        user.setId(userDTO.getId()); // Usar el ID proporcionado por el cliente
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());

        // Guardar el usuario en la base de datos
        User savedUser = userRepository.save(user);

        // Convertir la entidad guardada de vuelta a un DTO
        UserDTO savedUserDTO = new UserDTO();
        savedUserDTO.setId(savedUser.getId());
        savedUserDTO.setName(savedUser.getName());
        savedUserDTO.setLastName(savedUser.getLastName());
        savedUserDTO.setAge(savedUser.getAge());

        return savedUserDTO;
    }

    @Override
    public UserDTO[] getAllUsers() {
        List<User> users = userRepository.findAll();
        UserDTO[] userDTOs = new UserDTO[users.size()];
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setLastName(user.getLastName());
            userDTO.setAge(user.getAge());
            userDTOs[i] = userDTO;
        }
        return userDTOs;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDTO.getName());
            user.setLastName(userDTO.getLastName());
            user.setAge(userDTO.getAge());

            User updatedUser = userRepository.save(user);

            UserDTO updatedUserDTO = new UserDTO();
            updatedUserDTO.setId(updatedUser.getId());
            updatedUserDTO.setName(updatedUser.getName());
            updatedUserDTO.setLastName(updatedUser.getLastName());
            updatedUserDTO.setAge(updatedUser.getAge());

            return updatedUserDTO;
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}