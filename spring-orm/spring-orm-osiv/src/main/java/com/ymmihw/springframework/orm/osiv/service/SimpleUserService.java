package com.ymmihw.springframework.orm.osiv.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ymmihw.springframework.orm.osiv.model.BasicUser;
import com.ymmihw.springframework.orm.osiv.repository.UserRepository;

@Service
public class SimpleUserService implements UserService {

  private final UserRepository userRepository;

  public SimpleUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<BasicUser> findOne(String username) {
    return userRepository.findDetailedByUsername(username);
  }
}
