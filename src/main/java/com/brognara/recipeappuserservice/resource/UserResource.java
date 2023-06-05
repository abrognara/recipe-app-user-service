package com.brognara.recipeappuserservice.resource;

import com.brognara.recipeappuserservice.CreateUserRequest;
import com.brognara.recipeappuserservice.entity.User;
import com.brognara.recipeappuserservice.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserResource {

    private final UserRepository userRepository;

    @Autowired
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public ResponseEntity<User> login() {
        // TODO validate jwt (with google client lib), then extract google id and use here
        final long id = 0L;
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user.get());
    }

    // for dev work
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") final String idAsStr) {
        // validate id token
        long id;
        try {
            id = Long.parseLong(idAsStr);
        } catch (Exception e) {
            System.err.println("Failed to get user with id " + idAsStr + ": " + e);
            return ResponseEntity.badRequest().body(null);
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user.get());
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody final CreateUserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setJoinedTimestamp(String.valueOf(System.currentTimeMillis()));
        user.setGoogleAccountId(request.getGoogleAccountId());

        User createdUser = userRepository.save(user);
        System.out.println(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
