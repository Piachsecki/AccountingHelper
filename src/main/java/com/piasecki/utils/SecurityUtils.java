package com.piasecki.utils;

import com.piasecki.domain.User;
import com.piasecki.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

//@Service

/*
    @Component - Scope httprequest, nazwac userContext i uzywac go sobie w innych komponentach
    Ale to jest tworzone tylko raz - np. w konstruktorze @PostConstruct - mozna uzyc do tego
    Spring stworzy obiekt i wywola od razu ta funckje

    zapisujemy jak atrybut UserContext





    ale moje podejscie jest dobre, tylko nie opisywac tego Service
 */
public class SecurityUtils {
    public static User getCurrentUser(UserService userService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("Zalogowany użytkownik nie został znaleziony");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return userService.findByUsername(username);
    }
}
