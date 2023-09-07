package com.cg.model.user;

import com.cg.Enum.EGender;
import com.cg.Enum.ERole;
import com.cg.model.ParseModel;
import com.cg.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.cg.service.UserService.ManagerService.managers;

@Getter
@Setter
@NoArgsConstructor
public class Manager extends User implements Serializable, ParseModel<Manager> {


    public Manager(long id, String name, EGender gender, String phone, String address
            , String username, String password, ERole role) {
        this.setId(id);
        this.setName(name);
        this.setGender(gender);
        this.setPhone(phone);
        this.setAddress(address);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
    }


    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s", this.getId(), this.getName(), this.getGender()
                , this.getPhone(), this.getAddress(), this.getUsername(), this.getPassword(), this.getRole());
    }

    public static long getNextId() {
        long max = 0;
        if (managers != null) {
            for (Manager manager : managers) {
                if (manager.getId() > max) {
                    max = manager.getId();
                }
            }
        }
        return max + 1;
    }

    @Override
    public Manager parse(String line) {
        String[] items = line.split(",");
        Manager manager = null;
        try {
            manager = new Manager(Long.parseLong(items[0]), items[1], EGender.valueOf(items[2]), items[3]
                    , items[4], items[5], items[6], ERole.valueOf(items[7]));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return manager;
    }
}
