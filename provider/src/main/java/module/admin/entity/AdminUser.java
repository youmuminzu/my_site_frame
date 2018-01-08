package module.admin.entity;

public class AdminUserImpl {
    public int id;
    public String name;
    private String password;
    private String salt;
    private short status;
    private short roleId;
    private String createTime;
    private String updateTime;
    private AdminRoleImpl role;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public AdminRoleImpl getRole() {
        return role;
    }

    public void setRole(AdminRoleImpl role) {
        this.role = role;
    }

    public AdminRoleImpl getAdminRoleImpl() {
        return adminRoleImpl;
    }

    public void setAdminRoleImpl(AdminRoleImpl adminRoleImpl) {
        this.adminRoleImpl = adminRoleImpl;
    }

    private AdminRoleImpl adminRoleImpl;

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
