package com.cg.model.user;

import com.cg.Enum.EGender;
import com.cg.Enum.ERole;
import com.cg.model.ParseModel;
import com.cg.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.cg.service.UserService.StaffService.staffList;

@NoArgsConstructor
@Setter
@Getter
public class Staff extends User implements Serializable, ParseModel<Staff> {

    public Staff(long id, String name, EGender gender, String phone, String address
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

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s", this.getId(), this.getName(), this.getGender().getName()
                , this.getPhone(), this.getAddress(), this.getUsername(), this.getPassword(), this.getRole().getName());
    }

    public static long getNextId() {
        long max = 0;
        if (staffList != null) {
            for (Staff staff : staffList) {
                if (staff.getId() > max) {
                    max = staff.getId();
                }
            }
        }
        return max+1 ;
    }

    @Override
    public Staff parse(String line) {
        String[] items = line.split(",");
        Staff staff = null;
        try {
            staff = new Staff(Long.parseLong(items[0]), items[1], EGender.parse(items[2])
                    , items[3], items[4], items[5], items[6], ERole.parse(items[7]));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    public static void main(String[] args) {
        long max = 0;
        if (staffList != null) {
            for (Staff staff : staffList) {
                if (staff.getId() > max) {
                    max = staff.getId();
                }
            }
        }
        System.out.println(max);
    }
}
