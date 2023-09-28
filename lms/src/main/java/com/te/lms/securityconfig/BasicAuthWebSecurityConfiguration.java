package com.te.lms.securityconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthWebSecurityConfiguration
{
	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;
	
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(getUserDetailsService());
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin = User.withUsername("Admin").password(getPasswordEncoder().encode("admin")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(admin);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception{
		security.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/mentor/**").hasRole("MENTOR")
				.requestMatchers("/employee/**").hasRole("EMPLOYEE")
				.requestMatchers("/**").permitAll()
				.and()
//				.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
				.userDetailsService(userDetailsService())
				.formLogin()
				.loginPage("/signin").loginProcessingUrl("/userLogin")
				.successHandler(successHandler)
				.permitAll();
		return security.build();
	}
}