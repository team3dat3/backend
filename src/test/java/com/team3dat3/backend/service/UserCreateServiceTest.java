package com.team3dat3.backend.service;

import com.team3dat3.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserCreateServiceTest {
    @Autowired
    UserRepository userRepository;

}
