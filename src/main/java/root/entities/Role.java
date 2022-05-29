package root.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(
            Permission.CREATE,
            Permission.READ,
            Permission.UPDATE,
            Permission.DELETE)),

    USER(Set.of(
            Permission.READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }

    public static Role from(Collection<? extends GrantedAuthority> authorities) {
        return Role.valueOf(Role.class, authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(grantedAuthority -> grantedAuthority.startsWith("ROLE_"))
                .findFirst()
                .orElse(Role.USER.name())
                .replaceFirst("ROLE_", ""));
    }
}
