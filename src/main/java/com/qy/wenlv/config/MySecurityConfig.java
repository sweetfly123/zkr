package com.qy.wenlv.config;

import com.qy.wenlv.security.IgnoredUrlsProperties;
import com.qy.wenlv.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;

/**
 * 功能描述
 *
 * @author Barret
 * @date 8/7/2020
 * @return
 */
@EnableWebSecurity
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Autowired
    public MyUserDetailsService userDetailsService;

    /**
     * 认证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
       /* auth.inMemoryAuthentication() // 项目启动 账户-密码-角色 信息保存进内存中
//                .passwordEncoder(passwordEncoder())
                .withUser("aaa").password(passwordEncoder().encode("123456")).roles("VIP1")
                .and().withUser("ggg").password(passwordEncoder().encode("123456")).roles("VIP1, VIP2")
                .and().withUser("ccc").password(passwordEncoder().encode("123456")).roles("VIP1", "VIP2", "VIP3");
*/
    }

    /**
     * 登录处理
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //将不进行登录验证的url配置在yml文件中
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        for (String url : ignoredUrlsProperties.getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        // 开启登录配置
        registry
                // 标识访问 `/index` 这个接口，需要具备`ADMIN`角色
                .antMatchers("/index").hasRole("ADMIN")
                // 允许匿名的url - 可理解为放行接口 - 多个接口使用,分割
//                .antMatchers("/login", "/home").permitAll()
                // 其余所有请求都需要认证
                .anyRequest().authenticated()
                .and()
                // 设置登录认证页面
                .formLogin()
                // 登录成功后的处理接口 - 方式①
//                .loginProcessingUrl("/home")
                // 自定义登陆用户名和密码属性名，默认为 username和password
//                .usernameParameter("ass")
//                .passwordParameter("123456")
//                 登录成功后的处理器  - 方式②
                .successHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    RequestCache requestCache = new HttpSessionRequestCache();
                    SavedRequest savedRequest = requestCache.getRequest(req, resp);
                    String redirectUrl = savedRequest.getRedirectUrl();
                    System.out.println("初始访问路径：" + redirectUrl);
                    resp.sendRedirect(redirectUrl);
                })
                // 配置登录失败的回调
                .failureHandler((req, resp, exception) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("登录失败...");
                    out.flush();
                })
                .permitAll()//和表单登录相关的接口统统都直接通过
                .and()
                .logout().logoutUrl("/logout")
                // 配置注销成功的回调
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("注销成功...");
                    out.flush();
                })
                .permitAll()
                .and()
                .httpBasic()
                .and()
                // 关闭CSRF跨域
                .csrf().disable();

    }

    /**
     * 忽略拦截
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略url - 会直接过滤该url - 将不会经过Spring Security过滤器链
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/v2/**", "/webjars/**", "/static/**", "/favicon.ico", "/auth/users/register", "/users/register");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder：Spring Security 提供的加密工具，可快速实现加密加盐
        return new BCryptPasswordEncoder();
    }
}