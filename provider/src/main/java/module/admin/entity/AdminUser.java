package module.admin.entity;

public class AdminUser {
    public int id;
    public String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String password;
    private String salt;
    private short status;
    private short roleId;
    private String createTime;
    private String updateTime;
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    private AdminRole role;

    public AdminRole getRole() {
        return role;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    private AdminRole adminRole;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public short getRoleId() {
        return roleId;
    }

    public void setRoleId(short role) {
        this.roleId = role;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
