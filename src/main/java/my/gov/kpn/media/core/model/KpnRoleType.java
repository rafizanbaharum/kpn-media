package my.gov.kpn.media.core.model;

/**
 * @author rafizan.baharum
 * @since 7/12/13
 */
public enum KpnRoleType {

    ROLE_ADMINISTRATOR("Administrator"), // 0
    ROLE_USER("User"),                   // 1
    ROLE_GUEST("Guest");                 // 4

    private String description;

    KpnRoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
