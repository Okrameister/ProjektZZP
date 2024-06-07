package com.zawadzkia.springtodo.auth;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/auth")
class AuthController {

    @GetMapping("/login")
    String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/task";
        }
        return "auth/login";
    }
    
    @GetMapping("/error")
    String error() {
        throw new NotImplementedException("Not Implemented Yet!");
    }
}
