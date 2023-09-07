package com.cg.Enum;

public enum ERole {
    ADMIN(0, "Admin"),
    IMPORT(1, "Import"),
    SALES(2, "Sale"),
    INVENTORY(3, "Inventory"),
    AUDIT(4, "Audit");

    private ERole(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ERole findById(long id) {
        for (ERole e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public static ERole parse(String value) {
        if (value.equalsIgnoreCase("Import")) {
            return ERole.IMPORT;
        } else if (value.equalsIgnoreCase("Sale")) {
            return ERole.SALES;
        } else if (value.equalsIgnoreCase("Inventory")) {
            return ERole.INVENTORY;
        } else if (value.equalsIgnoreCase("Audit")) {
            return ERole.AUDIT;
        } else {
            // Xử lý trường hợp không hợp lệ
            return null;
        }
    }

    private long id;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
