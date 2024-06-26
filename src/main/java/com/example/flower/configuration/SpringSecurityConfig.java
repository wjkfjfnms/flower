package com.example.flower.configuration;

import com.example.flower.filter.TokenFilter;
import com.example.flower.service.impl.ErrorAuthenticationEntryPoint;
import com.example.flower.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@Lazy
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private ErrorAuthenticationEntryPoint errorAuthenticationEntryPoint;

    @Resource
    private TokenFilter tokenFilter;

//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        // 使用 BCryptPasswordEncoder 验证密码
//        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt 密码
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 配置 CSRF 关闭,允许跨域访问
        httpSecurity.csrf().disable();
        // 指定错误未授权访问的处理类
        httpSecurity.exceptionHandling().authenticationEntryPoint(errorAuthenticationEntryPoint);
        // 关闭 Session
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 允许发送权限码 发送邮件 登录 注册 接口文档的 api 的无授权访问，其他需要授权访问
        httpSecurity.authorizeRequests()
                .antMatchers("/api/user/passwordLogin", "/api/user/register", "/api/user/codeLogin", "/api/user/findPassword", "/api/user/getRequestPermissionCode", "/api/user/sendEmailCode","/doc.html","/swagger-ui.html","/swagger-resources/**","/webjars/**","/v3/api-docs")
                .permitAll().anyRequest().authenticated();
        // 添加拦截器
        httpSecurity.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
