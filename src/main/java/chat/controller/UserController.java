package chat.controller;

import chat.model.User;
import chat.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> read() {
        return new ResponseEntity<>(userRepository.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id) {
        User user = userRepository.getUser(id);
        return user != null ? new ResponseEntity<>(userRepository.getUser(id), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody User user) {
        userRepository.insertUser(user.getName(), user.getSurname(), user.getAge(), user.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User user) {
        if (userRepository.getUser(id) != null) {
            userRepository.updateUser(user.getName(), user.getSurname(), user.getAge(), user.getPassword(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        if (userRepository.getUser(id) != null) {
            userRepository.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
