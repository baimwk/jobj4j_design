package ru.job4j.generic;

public class Role extends Base{
    private final String permission;

    public Role(String id, String permission) {
        super(id);
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
