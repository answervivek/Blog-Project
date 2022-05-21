package com.vtripathi.blogproject.Payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtripathi.blogproject.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "Username must be at least 4 characters long")
    private String name;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", message = "Email must be valid")
    private String email;

    @NotEmpty
    @Size(min = 3,max=10, message = "Password must be at least 3 characters & maximum of 10 char long")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

}