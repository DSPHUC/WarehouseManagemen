package com.cg.service.UserService;

import com.cg.model.user.Manager;
import com.cg.service.CRUD;
import com.cg.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ManagerService implements CRUD<Manager> {
    private static final String fileManager = "D:\\case-study\\Supermarket\\data\\fileManager.txt";
    public static List<Manager> managers;

    static {
        managers = getAll1();
    }

    private ManagerService() {
        managers = getAll();
        if (managers == null) {
            managers = new ArrayList<>();
        }
    }

    public static void save() {
        FileUtils.writeData(fileManager, managers);
    }

    @Override
    public Manager findById(long id) {
        Manager manager = managers.stream()
                .filter(manager1 -> (manager1.getId() == id))
                .findFirst().orElse(null);
        if (manager == null) {
            System.out.println("Không tìm thấy Manager với ID: " + id);
        }
        return manager;
    }

    @Override
    public Manager findByName(String name) {
        Manager manager = managers.stream()
                .filter(manager1 -> (Objects.equals(manager1.getName(), name)))
                .findFirst().orElse(null);
        if (manager == null) {
            System.out.println("Không tìm thấy Manager với name: " + name);
        }
        return manager;
    }

    @Override
    public List<Manager> getAll() {
        return FileUtils.readData(fileManager, Manager.class);
    }

    public static List<Manager> getAll1() {
        return FileUtils.readData(fileManager, Manager.class);
    }


    @Override
    public void creat(Manager manager) {
        if (managers.stream().anyMatch(e ->
                Objects.equals(e.getUsername(), manager.getUsername()))) {
            System.out.println("tài khoản đã được đăng kí");
            return;
        }
        managers.add(manager);
        save();

    }

    @Override
    public void edit(long id, Manager manager) {
        managers.stream()
                .filter(manager1 -> manager1.getId() == id)
                .findFirst()
                .ifPresent(existingManager -> {
                    existingManager.setName(manager.getName());
                    existingManager.setGender(manager.getGender());
                    existingManager.setPhone(manager.getPhone());
                    existingManager.setAddress(manager.getAddress());
                    existingManager.setUsername(manager.getUsername());
                    existingManager.setPassword(manager.getPassword());
                });
        save();
    }

    @Override
    public void remove(long id) {
        managers = managers.stream()
                .filter(manager -> !Objects.equals(manager.getId(), id))
                .collect(Collectors.toList());
        save();
    }

    @Override
    public boolean isExist(long id) {
        Manager manager = managers.stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
        return manager != null;
    }

    public static Manager getByUserName(String userName) {
        return managers.stream()
                .filter(manager1 ->
                        manager1.getUsername().equals(userName))
                .findFirst().orElse(null);
    }

    public static Manager getByName(String name) {
        return managers.stream()
                .filter(manager1 ->
                        manager1.getName().equals(name))
                .findFirst().orElse(null);
    }
}
