package etf.unsa.ba.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import etf.unsa.ba.User.Entities.User;
import etf.unsa.ba.User.Entities.UserRole;
import etf.unsa.ba.User.Repositories.UserRepository;
import etf.unsa.ba.User.Repositories.UserRoleRepository;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@EnableDiscoveryClient
public class UserApplication {

	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner add_users(UserRepository userRepository,
									   UserRoleRepository userRoleRepository, 
									   BCryptPasswordEncoder bCryptPasswordEncoder) {
		return (args) -> {
			
			User administrator = new User("Admin","Admin","admin@gmail.com","admin", bCryptPasswordEncoder.encode("00000000"),"ADMIN");
			User ekrem = new User("Ekrem","Hasanovic","ekremh@gmail.com","ekrem", bCryptPasswordEncoder.encode("11111111"),"USER");
			User berina = new User("Berina","Smajovic", "berinas@gmail.com","berina",bCryptPasswordEncoder.encode("22222222"),"USER");
			User jasmina = new User("Jasmina","Celigija", "jasminac@gmail.com","jasmina", bCryptPasswordEncoder.encode("33333333"),"USER");
			
			UserRole admin = new UserRole(UserRole.Role.ADMIN); 
			UserRole user = new UserRole(UserRole.Role.USER); 
			userRoleRepository.save(admin);
			userRoleRepository.save(user);
			
			administrator.setUserRole(admin);
			ekrem.setUserRole(user);
			berina.setUserRole(user);
			jasmina.setUserRole(user);

			userRepository.save(administrator);
			userRepository.save(ekrem);
			userRepository.save(berina);
			userRepository.save(jasmina);
			
		};
	}
	

}