package com.workintech.service;

import com.workintech.entity.Role;
import com.workintech.entity.User;
import com.workintech.repository.RoleRepository;
import com.workintech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String fullName, String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new RuntimeException("This user is already exist: " + email);
        }
        String encodedPassword = passwordEncoder.encode(password);

        List<Role> roleList = new ArrayList<>();

        Optional<Role> optionalRole = roleRepository.findByAuthority("customer");
        if (!optionalRole.isPresent()) {
            Role roleUser = new Role();
            roleUser.setAuthority("customer");
            roleList.add(roleUser);
        } else {
            roleList.add(optionalRole.get());
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(roleList);

        return userRepository.save(user);
    }

    public User login(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            boolean hasPasswordMatch = passwordEncoder.matches(foundUser.getPassword(), user.getPassword());
            if (hasPasswordMatch) {
                return user;
            }
            throw new RuntimeException("Invalid user login");
        }
        throw new RuntimeException("User not found");
    }

}
