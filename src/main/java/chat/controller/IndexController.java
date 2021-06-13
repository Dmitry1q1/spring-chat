package chat.controller;

import chat.model.User;
import chat.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class IndexController {

    private final UserRepository userRepository;

    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity index() {
        Map<Object, Object> model = new HashMap<>();
        model.put("success", true);
        List<User> users = userRepository.getAllUsers();
//        model.put("name", "Dmitry");
        model.put("users", users);
        return ok(model);
    }

}
