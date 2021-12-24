package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }
    @Bean
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").permitAll();
        http.exceptionHandling().accessDeniedPage("/unauth.html");//自定义403页面 没有权限

        http.formLogin()    //自定义登录页面
        .loginPage("/login.html") //登录页面
        .loginProcessingUrl("/user/login") //登录访问路径
        .defaultSuccessUrl("/success.html").permitAll() //成功后跳转路径
        .and().authorizeRequests()
                .antMatchers("/img/**","/test/hello","/user/login").permitAll() //设置哪些路径可以直接访问，不需要认证
                //当前登录用户，只有具有admin权限才可以访问这个权限路径 //hasAuthority 对单一个权限
// 1               .antMatchers("/test/index").hasAuthority("admin")
// 2               .antMatchers("/test/index").hasAnyAuthority("admin,manger")
//                .antMatchers("/test/index").hasRole("sale")
//                .antMatchers("/test/update").hasRole("manager")
                .antMatchers("/test/index").hasAnyAuthority("admin,normal,Role_nor")
                .antMatchers("/user/**").hasAuthority("admin")
                .anyRequest().authenticated()
                .and().csrf().disable();//关闭csrf防护
    }
}
