package com.accountabilibuddy.api.configuration;

import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class CurrentUserAdvice {

    private final UserRepository userRepository;

    @ModelAttribute("currentUser")
    public User currentUser(Authentication authentication) {

        if (authentication == null || !(authentication.getPrincipal() instanceof OAuth2User oAuth2User)) {
            return null;
        }

        String email = oAuth2User.getAttribute("email");
        return userRepository.findByEmail(email).orElse(null);
    }
}
