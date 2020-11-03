package io.github.nrodrigoc.config;

import io.github.nrodrigoc.security.JwtAuthFilter;
import io.github.nrodrigoc.security.JwtService;
import io.github.nrodrigoc.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UsuarioServiceImpl usuarioService;

    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() { //Criptografar a senha do usuario
        return new BCryptPasswordEncoder();
//        PasswordEncoder passwordEncoder = new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) { //Serve para criar criptografia personalizada
//                return null;
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return false;
//            }
//        }
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        Usuário criado em memória
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("nathan")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN", "USER");
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/clientes/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")
                    .antMatchers("/api/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                        .permitAll()
                    .antMatchers(HttpMethod.POST,"/api/admin/**")
                        .hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
