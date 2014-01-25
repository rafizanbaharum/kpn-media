package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.KpnUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author rafizan.baharum
 * @since 7/10/13
 */
@Table(name = "KPN_USER")
@Entity(name = "KpnUser")
public class KpnUserImpl extends KpnPrincipalImpl implements KpnUser {

    @Column(name = "REALNAME")
    private String realname;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    public String getUsername() {
        return getName();
    }

    public void setUsername(String username) {
        setName(username);
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CmUserImpl{" +
                "name='" + getName() + '\'' +
                "realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
