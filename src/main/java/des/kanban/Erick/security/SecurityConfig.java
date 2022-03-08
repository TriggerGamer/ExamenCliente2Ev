package des.kanban.Erick.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import des.kanban.Erick.servicios.UsuarioServicio;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	 
	 /*@Autowired
	 private UsuarioServicio userDetailsService;*/
		
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                    .antMatchers(
	                    		"/",
	                            "/index",
	                            "/js/**",
	                            "/css/**",
	                            "/img/**",
	                            "/bootstrap/**",
	                            "/login",
	                            "/usuario/alta",
	                            "/usuario/crear",
	                            "/webjars/**").permitAll()
	                    .antMatchers(
	                    		"/proyecto/borrar",
	                    		"/proyecto/crear",
	                    		"/proyecto/buscar").hasAuthority("manager")
	                    .antMatchers(
	                    		"/proyecto/{idProyecto}",
	                    		"/tarea/crear",
	                    		"/tarea/ver",
	                    		"/tarea/modificar",
	                    		"/tarea/buscar",
	                    		"/usuarios/listar").hasAnyAuthority("trabajador", "manager")
	                    .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                    .loginPage("/login")
	                    .loginProcessingUrl("/login")
	                    .usernameParameter("usuario")
	                    .passwordParameter("password")
	                    .successHandler(myAuthenticationSuccessHandler())
	                    .permitAll()
	                .and()
	                .logout()
	                    .invalidateHttpSession(true)
	                    .clearAuthentication(true)
	                    .logoutUrl("/usuario/logout")
	                    .logoutRequestMatcher(new AntPathRequestMatcher("/usuario/logout"))
	                    .logoutSuccessUrl("/index")
	                    .permitAll()
	        		.and()
	        		.exceptionHandling().accessDeniedPage("/acceso-denegado");
	    }

		/*@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }*/
	    
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			return bCryptPasswordEncoder;
		}

		@Bean
		public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
			return new AuthenticationSuccessHandlerImpl();
		}
		
}