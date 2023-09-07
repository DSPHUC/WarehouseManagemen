package com.cg.Enum;

public enum EGender {
    MALE(1,"Nam"),
    FEMALE(2,"Nữ");

    private long id;
    private String name;

     EGender(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public static EGender findById(long id) {
        for (EGender e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

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
    public static EGender parse(String value) {
        if (value.equalsIgnoreCase("Nam")) {
            return EGender.MALE;
        } else if (value.equalsIgnoreCase("Nữ")) {
            return EGender.FEMALE;
        } else {
            return null;
        }
    }
}
