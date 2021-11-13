package one.typex.marketplacejava;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(filter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(authorizer -> authorizer
                        .antMatchers("/api/v1/login").permitAll()
                );
    }

    CustomUsernamePasswordAuthenticationFilter filter() throws Exception {
        var filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationFailureHandler((httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getWriter().print("Invalid login or password");
        });
        filter.setAuthenticationSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.getWriter().print("OK");
        });
        filter.setFilterProcessesUrl("/api/v1/login");
        filter.setPostOnly(true);
        return filter;
    }
}
