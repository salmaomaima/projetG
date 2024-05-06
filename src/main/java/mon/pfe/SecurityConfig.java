package mon.pfe;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void globalconfig(AuthenticationManagerBuilder auth, DataSource dataSource)throws Exception{
		/*
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN","PROF");
		auth.inMemoryAuthentication().withUser("prof1").password("123").roles("PROF");
		auth.inMemoryAuthentication().withUser("etd").password("123").roles("ETUDIANT");
		auth.inMemoryAuthentication().withUser("scolariter").password("123").roles("SCOLARITER");
	*/
	
//		Md5PasswordEncoder passwordEncoder= new Md5PasswordEncoder();
		auth.jdbcAuthentication()
			.dataSource(dataSource)
//			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery
			("select nom_utilisateur , mot_de_passe , true from employer where nom_utilisateur = ? ")
			.authoritiesByUsernameQuery
			("select employer.nom_utilisateur as principal, roles.nom_roles as role from employer,roles where employer.id_role = roles.id_roles and nom_utilisateur = ?")
			.rolePrefix("ROLE_")
			
			;
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**","/js/**","/images/**").permitAll()
				.antMatchers("/acceuil2").permitAll()
				.anyRequest()
					.authenticated()
		//pour que tous les requet soit authentifier
						.and()
						
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.defaultSuccessUrl("/getrolesuser")
		    .failureUrl("/error.html") 
			.and()
			.logout()
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.permitAll()
			
			;
	}

   

   
	
}