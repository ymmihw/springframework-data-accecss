package com.ymmihw.springframework.orm.osiv.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ymmihw.springframework.orm.osiv.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{username}")
  public ResponseEntity<?> findOne(@PathVariable String username) {
    return userService.findOne(username).map(DetailedUserDto::fromEntity).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}