package dev.caiovitor.eventticketing.security;


import dev.caiovitor.eventticketing.entity.Role;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

          User user = userRepository.findByEmailWithRoles(username)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(),user.getPassword(),mapRolesToAuthority(user.getRoles()));
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthority(Set<Role> roles){
        return roles.stream()
                .map(role-> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toSet());
    }
}
