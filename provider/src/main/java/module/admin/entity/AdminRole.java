package module.admin.entity;

import define.admin.entity.AdminPermission;
import java.util.List;

public class AdminRoleImpl {
    private int id;
    private String role;
    private String description;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }

    private List<Integer> permissionIds;
    private List<AdminPermission> permissions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AdminPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<AdminPermission> permissions) {
        this.permissions = permissions;
    }


}
