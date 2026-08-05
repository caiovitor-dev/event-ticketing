package dev.caiovitor.eventticketing.security;


import dev.caiovitor.eventticketing.entity.Role;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.enums.RoleName;
import dev.caiovitor.eventticketing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByEmail(username).;

        if(user == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(),user.getPassword(),mapRolesToAuthority(user.getRoles()));
    }

    public Collection<GrantedAuthority> mapRolesToAuthority(Set<Role> roles){
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toSet());
    }
}
