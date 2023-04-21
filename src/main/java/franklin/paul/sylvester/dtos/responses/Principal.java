package franklin.paul.sylvester.dtos.responses;

import java.util.Date;

public class Principal {
    private String userId;
    private String username;
    private String email;
    private Date registered;
    private boolean isActive;
    private String roleId;
    private String token;

    public Principal() {
        super();
    }

    public Principal(String userId, String username, String email, Date registered, boolean isActive, String roleId) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.registered = registered;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", registered=" + registered +
                ", isActive=" + isActive +
                ", roleId='" + roleId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
