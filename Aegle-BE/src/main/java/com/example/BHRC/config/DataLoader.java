package com.example.BHRC.config;

import com.example.BHRC.model.Roles;
import com.example.BHRC.model.User;
import com.example.BHRC.repository.RolesRepository;
import com.example.BHRC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${data.loadOnStartup}")
    private boolean loadDataOnStartup;

    @Autowired
    public DataLoader(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) {
        if (loadDataOnStartup) {
            loadDefaultData();
        }
        createAdminUser();
    }

    private void loadDefaultData() {
        createRoles(RolesConstants.ADMIN.getRoleName(), RolesConstants.ADMIN.getRoleType());
        createRoles(RolesConstants.MANAGER.getRoleName(), RolesConstants.MANAGER.getRoleType());
        createRoles(RolesConstants.STAFF.getRoleName(), RolesConstants.STAFF.getRoleType());
        createRoles(RolesConstants.USER.getRoleName(), RolesConstants.USER.getRoleType());
    }

    private void createRoles(String roleName, Integer roleType) {
        Roles existingRole = rolesRepository.getByRoleType(roleType);
        if (existingRole == null) {
            Roles role = new Roles();
            role.setRoleName(roleName);
            role.setRoleType(roleType);
            rolesRepository.save(role);
        }
    }

    private void createAdminUser() {
        // Create the role if it doesn't exist
        Roles adminRole = rolesRepository.findByRoleName(RolesConstants.ADMIN.getRoleName())
                .orElseGet(() -> {
                    Roles role = new Roles();
                    role.setRoleName(RolesConstants.ADMIN.getRoleName());
                    return rolesRepository.save(role);
                });

        // Create admin user if it doesn't exist
        if (userRepository.findByUsername("admin").isEmpty()) {
            Set<Roles> rolesSet = new HashSet<>();
            rolesSet.add(adminRole);
            User adminUser = new User("admin", passwordEncoder.encode("password"), rolesSet);
            userRepository.save(adminUser);

            // Sync the bidirectional relationship
            adminRole.getUsers().add(adminUser);

            rolesRepository.save(adminRole);
            userRepository.save(adminUser);

            System.out.println("Admin user created!");
        }
    }
}
