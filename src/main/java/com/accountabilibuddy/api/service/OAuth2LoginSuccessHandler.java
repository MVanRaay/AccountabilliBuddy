package com.accountabilibuddy.api.service;

import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        String email = oauth2User.getAttribute("email");
        String googleId = oauth2User.getAttribute("sub");
        String firstName = oauth2User.getAttribute("given_name");
        String lastName = oauth2User.getAttribute("family_name");

        if (email == null) {
            throw new IllegalStateException("Google did not return an email");
        }

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User u = new User();
                    u.setEmail(email);
                    u.setGoogleId(googleId);
                    u.setFirstName(firstName);
                    u.setLastName(lastName);
                    u.setRegisteredOn(java.time.LocalDateTime.now());
                    u.setProfileComplete(false);
                    return userRepository.save(u);
                });

        if (!user.isProfileComplete()) {
            getRedirectStrategy().sendRedirect(request, response, "/users/register");
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, "/goals");
    }
}


