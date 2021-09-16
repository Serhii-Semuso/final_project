package com.epam.finalproject.payments.db.entity;

public enum Role {

    ADMIN, CLIENT;

    public static Role getRole(User user) {
        Long roleId = user.getRoleId();
        return Role.values()[roleId.intValue()];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
