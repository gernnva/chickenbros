
package com.chickenbros;
import com.chickenbros.Servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfiguracionesSeguridad extends WebSecurityConfigurerAdapter {

    //busca un usuario por nombre de usuario en la base de datos
    @Autowired
    private ServicioCliente ServCliente;

// configuracion del manejador de seguridad de Spring security
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(ServCliente)//le decimos cual es el servicio para auntenticar un cliente,
            .passwordEncoder(new BCryptPasswordEncoder());//luego de que encuentra el usuario le decimos cual es el encoder para comparar contraseñas
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()//cualquier usuario sin estar logueado //puede acceder a estos archivos
                .and().formLogin()//configuramos el login                                                             
                        .loginPage("/cliente/login") // Donde esta mi login
                        .loginProcessingUrl("/logincheck")//url que autentica un cliente
                        .usernameParameter("email") // Con que nombre viajan los datos del logueo
                        .passwordParameter("password")
                        .defaultSuccessUrl("/pedido/listar").permitAll() // A que URL ingresa si el usuario se autentica con exito
                .and().logout() // Aca configuro la salida
                        .logoutUrl("/logout")//sprin security desloguea desde esta url
                        .logoutSuccessUrl("/login?logout").permitAll()//y nos redirige aca
                .and().csrf().disable();
    }
}
