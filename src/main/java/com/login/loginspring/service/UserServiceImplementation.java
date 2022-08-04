package com.login.loginspring.service;

import com.login.loginspring.domain.Role;
import com.login.loginspring.domain.User;
import com.login.loginspring.repository.RoleRepository;
import com.login.loginspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Array;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Salvando novo usuário {} no banco de dados", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(String name, Long id){
        Optional<User> op = userRepository.findById(id);
        if(op.isPresent()) {
            User obj = op.get();
            if (obj.getName() != name) {
                obj.setName(name);
            }
            userRepository.save(obj);
            return obj;
        }
        return null;
    }

    @Override
    public void deleteUser(Long id){
        log.info("Deletando usuário {} do banco de dados", id);
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Salvando novo papel {} no banco de dados", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRouleToUser(String username, String roleName) {
        log.info("Adicionando papel {} ao usuario {}", roleName, username);
        User user = userRepository.findByUsername((username));
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Buscando usuario {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Buscando todos os usuarios");
        return userRepository.findAll();
    }
}
