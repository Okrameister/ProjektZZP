package com.zawadzkia.springtodo.user;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(UserModel user) {
        user.setPassword("{noop}" + user.getPassword());
        user.setEnabled(true);
        userRepository.save(user);
    }
}
