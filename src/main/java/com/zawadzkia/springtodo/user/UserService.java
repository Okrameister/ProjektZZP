package com.zawadzkia.springtodo.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.zawadzkia.springtodo.task.TaskModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserModel existingUser(String username) {
        Optional<UserModel> userModel = userRepository.findByUsername(username);
        return userModel.orElse(null);
    }

    public void saveUser(UserModel user) {
        user.setPassword("{noop}" + user.getPassword());
        user.setEnabled(true);
        userRepository.save(user);
    }
}
