package com.reebake.example;

import com.reebake.mwcs.permission.service.PermissionAuthService;
import com.reebake.mwcs.security.model.User;
import com.reebake.mwcs.user.entity.UserCredential;
import com.reebake.mwcs.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DaoUserDetailsService implements UserDetailsService {
    private final UserAuthService userAuthService;
    private final PermissionAuthService permissionAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userAuthService.queryByUsername(username);
        if(userCredential == null) {
            throw new UsernameNotFoundException("username not found");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        permissionAuthService.queryRoleCodeByUserId(userCredential.getUserId()).forEach(it -> {
            authorities.add(new SimpleGrantedAuthority(it));
        });
        User user = new User();
        user.setUserId(userCredential.getUserId());
        user.setUsername(username);
        user.setPassword(userCredential.getPassword());
        user.setAuthorities(authorities);

        user.setAccountNonExpired(userCredential.getAccountNonExpired());
        user.setCredentialsNonExpired(userCredential.getCredentialsNonExpired());
        user.setAccountNonLocked(userCredential.getAccountNonLocked());
        user.setEnabled(userCredential.getAccountEnabled());

        return user;
    }
}
