package module.admin.entity;

public class AdminPermission {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(String availableResources) {
        this.availableResources = availableResources;
    }

    private int id;
    private String permissionName;
    private String description;
    private String availableResources;
}
