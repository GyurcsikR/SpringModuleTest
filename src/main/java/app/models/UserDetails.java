package app.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDateTime workStart;

    private LocalDateTime workEnd;

    private boolean isEnabled;

    private boolean admin;



    public UserDetails() {
        this.isEnabled = true;
    }

    public UserDetails(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public UserDetails(Long id, String username, String password, LocalDateTime workStart, LocalDateTime workEnd, boolean isEnabled, boolean admin) {
        this(username, password);
        this.id = id;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.isEnabled = isEnabled;
        this.admin = admin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDateTime workStart) {
        this.workStart = workStart;
    }

    public LocalDateTime getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalDateTime workEnd) {
        this.workEnd = workEnd;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }
}
