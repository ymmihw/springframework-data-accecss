package com.ymmihw.springframework.orm.osiv;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.ymmihw.springframework.orm.osiv.model.BasicUser;
import com.ymmihw.springframework.orm.osiv.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerIntegrationTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    BasicUser user = new BasicUser();
    user.setUsername("root");
    user.setPermissions(new HashSet<>(Arrays.asList("PERM_READ", "PERM_WRITE")));

    userRepository.save(user);
  }

  @Test
  public void givenTheUserExists_WhenOsivIsEnabled_ThenLazyInitWorkEverywhere() throws Exception {
    mockMvc.perform(get("/users/root")).andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("root"))
        .andExpect(jsonPath("$.permissions", containsInAnyOrder("PERM_READ", "PERM_WRITE")));
  }

  @After
  public void flushDb() {
    userRepository.deleteAll();
  }
}
