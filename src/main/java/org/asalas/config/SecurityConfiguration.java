package org.asalas.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/**", "/resources", "/login", "/templates/**", "/webjars/**", "/vendor/**", "/css/**")
        .permitAll().and().authorizeRequests().antMatchers("/console/**").permitAll() 
        .and().formLogin().loginPage("/login")
		.successForwardUrl("/dashboard").permitAll().and().logout().permitAll();
 
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
 
}
