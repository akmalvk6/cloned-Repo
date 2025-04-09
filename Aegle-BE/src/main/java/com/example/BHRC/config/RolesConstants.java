package com.example.BHRC.config;

public enum RolesConstants {

    ADMIN(1, "Admin"),
    STAFF(2, "Staff"),
    MANAGER(3, "Manager"),
    USER(4, "User");

    private final Integer roleType;
    private final String roleName;

    RolesConstants(Integer roleType, String roleName) {
        this.roleType = roleType;
        this.roleName = roleName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RolesConstants fromRoleType(Integer roleType) {
        for (RolesConstants role : RolesConstants.values()) {
            if (role.getRoleType().equals(roleType)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No Role found with roleType " + roleType);
    }
}
