package chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class IndexController {

    @GetMapping("/")
    public ResponseEntity index() {
        Map<Object, Object> model = new HashMap<>();
        model.put("success", true);
        model.put("name", "Dmitry");
        return ok(model);
    }
}
