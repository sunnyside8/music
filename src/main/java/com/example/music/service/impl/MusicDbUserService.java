package com.example.music.service.impl;

import com.example.music.model.entity.UserEntity;
import com.example.music.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class MusicDbUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public MusicDbUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name" + username + "was not found"));

        return mapToUserDetails(userEntity);
    }


    private UserDetails mapToUserDetails(UserEntity userEntity) {
        List<GrantedAuthority> authorities =
                userEntity.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role
                                .getRole().name()))
                        .collect(Collectors.toList());

        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                authorities
                );
    }

}
