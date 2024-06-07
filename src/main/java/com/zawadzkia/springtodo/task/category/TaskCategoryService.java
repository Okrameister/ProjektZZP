package com.zawadzkia.springtodo.task.category;

import com.zawadzkia.springtodo.auth.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCategoryService {

    private final TaskCategoryRepository taskCategoryRepository;

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

}
