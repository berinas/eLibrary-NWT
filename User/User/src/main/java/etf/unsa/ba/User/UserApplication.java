package etf.unsa.ba.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import etf.unsa.ba.User.Entities.User;
import etf.unsa.ba.User.Entities.UserAccount;
import etf.unsa.ba.User.Entities.UserRole;
import etf.unsa.ba.User.Repositories.UserAccountRepository;
import etf.unsa.ba.User.Repositories.UserRepository;
import etf.unsa.ba.User.Repositories.UserRoleRepository;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner add_users(UserRepository userRepository,
									   UserAccountRepository userAccountRepository, 
									   UserRoleRepository userRoleRepository) {
		return (args) -> {
		
			User ekrem = new User("Ekrem","Hasanovic","ekremh@gmail.com");
			User berina = new User("Berina","Smajovic", "berinas@gmail.com");
			User jasmina = new User("Jasmina","Celigija", "jasminac@gmail.com");
			User jasmina1 = new User("Jasmina","mm", "jasminac@gmail.com");
			UserAccount eAccount = new UserAccount("ekrem", "123");
			UserAccount bAccount = new UserAccount("berina", "123");
			UserAccount jAccount = new UserAccount("jasmina", "123");
			UserAccount jAccount1 = new UserAccount("jasmina", "123");
			
			UserRole admin = new UserRole("admin"); 
			UserRole user = new UserRole("user"); 
			userRoleRepository.save(admin);
			userRoleRepository.save(user);
			
			
			ekrem.setUserRole(user);
			ekrem.setUserAccount(eAccount);
			eAccount.setUser(ekrem);
			berina.setUserRole(user);
			berina.setUserAccount(bAccount);
			bAccount.setUser(berina);
			jasmina.setUserRole(user);
			jasmina.setUserAccount(jAccount);
			
			jAccount.setUser(jasmina);
			jasmina1.setUserRole(user);
			jasmina1.setUserAccount(jAccount1);
			
			userRepository.save(jasmina1);
			
			userRepository.save(ekrem);
			userRepository.save(berina);
			userRepository.save(jasmina);
			userAccountRepository.save(eAccount);
			userAccountRepository.save(bAccount);
			userAccountRepository.save(jAccount);
			
		};
	}
	

}
