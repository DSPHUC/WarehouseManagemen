package com.cg.service.UserService;

import com.cg.Enum.ERole;
import com.cg.model.user.Staff;
import com.cg.service.CRUD;
import com.cg.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class StaffService implements CRUD<Staff> {
    private static final String fileStaff = "D:\\case-study\\Supermarket\\data\\" +
            "fileStaff.txt";
    public static Staff currentStaff;
    public static List<Staff> staffList;

    static {
        staffList = getAll1();
    }

    public StaffService() {
        staffList = getAll();
        if (staffList == null) {
            staffList = new ArrayList<>();
        }
    }

    public static void save() {
        FileUtils.writeData(fileStaff, staffList);
    }


    @Override
    public Staff findById(long id) {
//        staffList = getAll();
        Staff staff = staffList.stream()
                .filter(staff1 -> (staff1.getId() == id))
                .findFirst().orElse(null);
        if (staff == null) {
            System.out.println("Không tìm thấy staff với ID: " + id);
        }
        return staff;
    }

    @Override
    public Staff findByName(String name) {
//        staffList = getAll();
        Staff staff = staffList.stream()
                .filter(staff1 -> (Objects.equals(staff1.getName(), name)))
                .findFirst().orElse(null);
        if (staff == null) {
            System.out.println("Không tìm thấy staff với name: " + name);
        }
        return staff;
    }

    @Override
    public void creat(Staff staff) {
//        staffList = getAll();
        if (staffList.stream().anyMatch(e ->
                Objects.equals(e.getUsername(), staff.getUsername()))) {
            System.out.println("Staff đã được sử dụng");
            return;
        }
        staffList.add(staff);
        save();
    }

    @Override
    public void edit(long id, Staff staff) {
        staffList.stream()
                .filter(staff1 -> staff1.getId() == id)
                .findFirst()
                .ifPresent(staff2 -> {
                    staff2.setName(staff.getName());
                    staff2.setGender(staff.getGender());
                    staff2.setPhone(staff.getPhone());
                    staff2.setAddress(staff.getAddress());
                    staff2.setUsername(staff.getUsername());
                    staff2.setPassword(staff.getPassword());
                });
        save();
    }

    @Override
    public void remove(long id) {
//        staffList = getAll();
        staffList = staffList.stream().filter(staff1 ->
                        !Objects.equals(staff1.getId(), id))
                .collect(Collectors.toList());
        save();
    }

    @Override
    public boolean isExist(long id) {
        Staff staff = staffList.stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
        return staff != null;
    }

    public static Staff getByUserName(String userName) {
        return staffList.stream()
                .filter(staff1 ->
                        staff1.getUsername().equals(userName))
                .findFirst().orElse(null);
    }

    public static Staff getByName(String name) {
        return staffList.stream()
                .filter(staff1 ->
                        staff1.getName().equals(name))
                .findFirst().orElse(null);
    }

//    public static Staff getRole(String role) {
//        return staffList.stream()
//                .filter(staff1 ->
//                        staff1.getRole().equals(role))
//                .findFirst().orElse(null);
//    }

    @Override
    public List<Staff> getAll() {
        return FileUtils.readData(fileStaff, Staff.class);
    }

    public static List<Staff> getAll1() {
        return FileUtils.readData(fileStaff, Staff.class);
    }

    public Optional<ERole> checkRole(String username) {
        return staffList.stream()
                .filter(e -> e.getUsername().equals(username))
                .map(Staff::getRole)
                .findFirst();
    }
}
