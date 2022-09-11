package rs.atekom.telegram.security;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vaadin.flow.component.UI;

import rs.atekom.telegram.data.Role;
import rs.atekom.telegram.data.entity.Token;
import rs.atekom.telegram.data.entity.User;
import rs.atekom.telegram.data.service.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(),
                    getAuthorities(user));
        }
    }

    public void authenticate(Token token) {
    	if(token.getId() != null) {
    		User user = new User();
    		user.setTelegramId(token.getId());
    		user.setName(token.getFirst_name());
    		user.setUsername(token.getUsername());
    		user.setLastName(token.getLast_name());
    		user.setProfilePictureUrl(token.getPhoto_url());
    		user.setHashedPassword(token.getHash());
    		user.setRoles(Collections.singleton(Role.USER));
    		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getHashedPassword(), getAuthorities(user));
    		SecurityContextHolder.getContext().setAuthentication(authToken);
    		UI.getCurrent().getSession().setAttribute("user", user);
    	}
    }
    
    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

    }

}
