package com.cg.Enum;

public enum EType {
    INPUT(1, "Phiếu nhập kho"),
    OUTPUT(2, "Phiếu xuất kho");
    private int id;
    private String name;

     EType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static EType findById(long id) {
        for (EType e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    public static EType findByName(String name) {
        for (EType e : values()) {
            if (e.getName() == name) {
                return e;
            }
        }
        return null;
    }

    public static EType parse(String value) {
        if (value.equalsIgnoreCase("Phiếu nhập kho")) {
            return EType.INPUT;
        } else if (value.equalsIgnoreCase("Phiếu xuất kho")) {
            return EType.OUTPUT;
        } else {
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
