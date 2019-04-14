package etf.unsa.ba.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner add_users(UserRepository userRepository,
									   UserRoleRepository userRoleRepository) {
		return (args) -> {
		
			User ekrem = new User("Ekrem","Hasanovic","ekremh@gmail.com","ekrem", "11111111");
			User berina = new User("Berina","Smajovic", "berinas@gmail.com","berina", "22222222");
			User jasmina = new User("Jasmina","Celigija", "jasminac@gmail.com","jasmina", "33333333");
			
			UserRole admin = new UserRole(UserRole.Role.ADMIN); 
			UserRole user = new UserRole(UserRole.Role.USER); 
			userRoleRepository.save(admin);
			userRoleRepository.save(user);
			
			
			ekrem.setUserRole(user);
			berina.setUserRole(user);
			jasmina.setUserRole(user);

		
			userRepository.save(ekrem);
			userRepository.save(berina);
			userRepository.save(jasmina);
			
		};
	}
	

}
