package org.asalas.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//	 http.csrf().ignoringAntMatchers("/h2-console/**").disable()
//	
//		.headers().frameOptions().disable()
//		.and().authorizeRequests().antMatchers("/**/favicon.ico").permitAll()
//		.and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
//		.and().authorizeRequests().antMatchers("/product/**").permitAll()
//		.and().authorizeRequests().antMatchers("/webjars/**").permitAll()
//		.and().authorizeRequests().antMatchers("/static/css").permitAll()
//		.and().authorizeRequests().antMatchers("/js").permitAll()
//		.and().authorizeRequests().antMatchers("/first_user").permitAll()
//		//.and().authorizeRequests().antMatchers("/users").hasRole("ADMIN")
//		.and().formLogin()
//			.loginPage("/login").permitAll().defaultSuccessUrl("/dashboard")
//		.and().logout().permitAll();

		http.authorizeRequests()
			.and().authorizeRequests().antMatchers("/h2console/**").permitAll()
			.and().authorizeRequests().antMatchers("/first_user").permitAll()
				.antMatchers("/resources", "/login", "/templates/**", "/webjars/**", "/vendor/**", "/css/**")
				.permitAll().anyRequest().authenticated()
				.and()
					.authorizeRequests().antMatchers("/**").hasAnyRole("ADMIN", "CUSTOMER")
				.and()
					.formLogin()
					.loginPage("/login")
					.successForwardUrl("/dashboard")
				.and()
			      .rememberMe()
			        .tokenRepository(persistentTokenRepository())
			        .key("AppKey")
			        .alwaysRemember(true)
			        .rememberMeParameter("remember-me")
			        .rememberMeCookieName("chocolate-remember-me")
			        .tokenValiditySeconds(24 * 60 * 60)
			     .and().logout().permitAll().logoutSuccessUrl("/login")
				;
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
	    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
	    tokenRepository.setDataSource(dataSource);
	    return tokenRepository;
	}
}