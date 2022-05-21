package com.vtripathi.blogproject;

import com.vtripathi.blogproject.Config.AppConstants;
import com.vtripathi.blogproject.Entity.Role;
import com.vtripathi.blogproject.Repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogProjectApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BlogProjectApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(this.passwordEncoder.encode("1234"));

        try {
            Role roleA = new Role();
            roleA.setId(AppConstants.ADMIN_USER);
            roleA.setName("ROLE_ADMIN");

            Role roleM = new Role();
            roleM.setId(AppConstants.MANAGER_USER);
            roleM.setName("ROLE_MANAGER");

            Role roleN = new Role();
            roleN.setId(AppConstants.NORMAL_USER);
            roleN.setName("ROLE_NORMAL");

            List<Role> roles = List.of(roleA, roleM, roleN);
            List<Role> saveRoles = this.roleRepo.saveAll(roles);

            saveRoles.forEach(role -> {
                System.out.println(role.getName());
            });

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}