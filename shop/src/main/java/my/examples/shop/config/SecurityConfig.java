package my.examples.shop.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// spring security 설정 파일은 보통
// WebSecurityConfigurerAdapter 를 상속받아서 만든다.
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 아예 인가처리를 하지 않는 (무시하는 URL설정) - 이미지 or css, javascript
    @Override
    public void configure(WebSecurity web) throws Exception {

//    PathRequest.toStaticResources().atCommonLocations()
//    CSS(new String[]{"/css/**"}),
//    JAVA_SCRIPT(new String[]{"/js/**"}),
//    IMAGES(new String[]{"/images/**"}),
//    WEB_JARS(new String[]{"/webjars/**"}),
//    FAVICON(new String[]{"/**/favicon.ico"});

        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"))
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/main")
                    .permitAll().and()
                .authorizeRequests() // 인가에 대한 설정
                    .antMatchers("/users/join").permitAll()
                    .antMatchers("/users/welcome").permitAll()
                    .antMatchers("/users/login").permitAll()
                    .antMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/main").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                    .and()
                .csrf().ignoringAntMatchers("/**") // h2-console로그인창이 csrf를 지원.
                .and().headers().frameOptions().disable() // h2-console을 사용하려면 설정
                .and().formLogin() // 사용자가 정의하는 로그인 화면을 만들겠다.
                    .loginProcessingUrl("/users/login") // 로그인 화면
                    .loginPage("/users/login") // 사용자가 입력한 id, password가 전달되는 url경로(필터가처리)
                            .usernameParameter("email")
                            .passwordParameter("password")
                    .failureUrl("/users/login?fail=true");

    }
}
