package chat.controller;

import chat.model.User;
import chat.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Object, Object> model = new HashMap<>();
        model.put("success" , true);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User user) {
        Map<Object, Object> model = new HashMap<>();
        if (userRepository.getUser(id) != null) {
            model.put("success" , true);
            userRepository.updateUser(user.getName(), user.getSurname(), user.getAge(), user.getPassword(), id);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            model.put("success" , false);
            return new ResponseEntity<>(model, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        Map<Object, Object> model = new HashMap<>();
        if (userRepository.getUser(id) != null) {
            model.put("success", true);
            userRepository.deleteUser(id);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            model.put("success", false);
            return new ResponseEntity<>(model, HttpStatus.NOT_MODIFIED);
        }
    }
}
