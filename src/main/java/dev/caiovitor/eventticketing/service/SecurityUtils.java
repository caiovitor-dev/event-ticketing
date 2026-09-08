package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class SecurityUtils {

    public User getLoggedUser(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof CustomUserDetails customUserDetails){
           return customUserDetails.getUser();
        }

        return null;
    }
}
