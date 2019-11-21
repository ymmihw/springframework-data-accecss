package com.ymmihw.springframework.orm.osiv.service;

import java.util.Optional;
import com.ymmihw.springframework.orm.osiv.model.BasicUser;

public interface UserService {
  Optional<BasicUser> findOne(String username);
}
