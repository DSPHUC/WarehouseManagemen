package com.cg.model;

import com.cg.Enum.EGender;
import com.cg.Enum.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public abstract class User implements Serializable {
    private long id;
    private String name;
    private EGender gender;
    private String phone;
    private String address;
    private ERole role;
    private String username;
    private String password;

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
