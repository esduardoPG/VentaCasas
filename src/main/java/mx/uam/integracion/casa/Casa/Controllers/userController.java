package mx.uam.integracion.casa.Casa.Controllers;

import mx.uam.integracion.casa.Casa.dto.UserDTO;
import mx.uam.integracion.casa.Casa.service.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class userController {
    private final userService userService;

    public userController(userService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = Arrays.asList(userService.getAllUsers());
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }
}