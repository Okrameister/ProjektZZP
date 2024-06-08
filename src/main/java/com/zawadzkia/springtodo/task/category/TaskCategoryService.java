package com.zawadzkia.springtodo.task.category;

import com.zawadzkia.springtodo.auth.AppUserDetails;
import com.zawadzkia.springtodo.task.TaskModel;
import com.zawadzkia.springtodo.user.UserModel;
import com.zawadzkia.springtodo.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCategoryService {

    private final TaskCategoryRepository taskCategoryRepository;

    private final UserRepository userRepository;

    public List<TaskCategoryModel> getUserTaskCategoryList() {
        ArrayList<TaskCategoryModel> result = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AppUserDetails userDetails) {
            List<TaskCategoryModel> allByOwner = taskCategoryRepository.findAllByOwner(userDetails.getUser());
            allByOwner.forEach(category -> result.add(new TaskCategoryModel(category.getId(), category.getName(),
                    category.getDescription(), category.getOwner())));
        }
        return result;
    }

    public void saveCategory(TaskCategoryModel category) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel user = userRepository.findByUsername(username).orElseThrow();


        category.setOwner(user);
        
        taskCategoryRepository.save(category);
    }
}
