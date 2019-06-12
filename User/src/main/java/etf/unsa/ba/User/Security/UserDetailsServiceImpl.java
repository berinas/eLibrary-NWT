package etf.unsa.ba.User.Security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import etf.unsa.ba.User.Entities.UserRole;
import etf.unsa.ba.User.Services.UserService;


@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
		final List<etf.unsa.ba.User.Entities.User> users = userService.findAll();
		
		System.out.println("Trazim usera...");

        for(etf.unsa.ba.User.Entities.User appUser: users) {
            System.out.println(username);
			if(appUser.getUsername().equals(username)) {
				
		
				System.out.println("Nasao usera!");
				
				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
						//.commaSeparatedStringToAuthorityList("ROLE_" + "ADMIN");
				
				userService.saveAndSendUserInfo(appUser.getUsername(), appUser.getPassword());
				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
				// And used by auth manager to verify and check user authentication.
				return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
			}
		}
		
		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
    }
    catch(Exception ex) {
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }
}
	

}