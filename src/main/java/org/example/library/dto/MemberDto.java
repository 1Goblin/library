package org.example.library.dto;


import lombok.Getter;
import lombok.Setter;
import org.example.library.entity.Role;

@Getter
@Setter
public class MemberDto {

    private String username;
    //    private String password;
    private Role role;
}