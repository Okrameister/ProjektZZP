package com.zawadzkia.springtodo.task.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zawadzkia.springtodo.task.TaskModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping({"/category"})
@Slf4j
public class TaskCategoryController {
    private final TaskCategoryService taskCategoryService;
    
    @GetMapping("/create")
    public ModelAndView createCategory() {
        ModelAndView model = new ModelAndView("/task/categories/createCategory");
        model.addObject("category", new TaskCategoryModel());
        return model;
    }

    @PostMapping("/create")
    public String saveCategory(@ModelAttribute TaskCategoryModel category) {
        taskCategoryService.saveCategory(category);
        return "redirect:/task";
    }
}
