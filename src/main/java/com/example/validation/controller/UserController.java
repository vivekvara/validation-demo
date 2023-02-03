package com.example.validation.controller;

import com.example.validation.dto.UserDto;
import com.example.validation.entity.User;
import com.example.validation.exception.ResourceNotFoundException;
import com.example.validation.mapper.UserMapper;
import com.example.validation.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userMapper.map((List<User>) userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    ResponseEntity<UserDto> getById(@PathVariable("id") @Min(1) Long id) {
        User usr = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID :" + id + " Not Found!"));

        return ResponseEntity.ok().body(userMapper.map(usr));
    }

    @GetMapping("/users/username")
    ResponseEntity<UserDto> getByUsername(@RequestParam() String username) {
        User usr = userRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException(username + " NOT Found!"));

        return ResponseEntity.ok().body(userMapper.map(usr));
    }

    @PostMapping("/users")
    ResponseEntity<?> addUser(@Valid @RequestBody UserDto user) {
        User addeduser = userRepository.save(userMapper.map(user));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addeduser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> update(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody UserDto userDto) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID :" + id + " Not Found!"));

        User user = userMapper.map(userDto);
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<String> delete(@PathVariable("id") @Min(1) Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID :" + id + " Not Found!"));

        userRepository.deleteById(user.getId());
        return ResponseEntity.ok().body("User deleted with success!");
    }
}
