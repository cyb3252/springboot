package com.cyb.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author cyb
 * @date 2018/12/21 - 14:46
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("Vip1")
                .antMatchers("/level2/**").hasRole("Vip2")
                .antMatchers("/level3/**").hasRole("Vip3")
        ;
        //开启自动配置的登录功能
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");  //告诉spring登录发送什么请求  默认的是/login
        //1、 /login来到登录页面
        //2、重定向到/login?error表示登录失败
        //3、默认post形式的/login表示处理登录
        //4、一旦定制loginPage;那么LoginPage的post请求就是登录

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");  //注销成功以后返回首页
        //以下是默认的规则，我们也可以修改这些默认的规则
        //1、访问/logout表示用户注销，清空session
        //2、注销成功会返回/login?logout 页面

        //开启记住我的功能
        http.rememberMe().rememberMeParameter("remember");
        //登录成功以后，将cookie发给浏览器保存，将以后页面带上这个cookie，只要通过检查就可以免登陆了
        //点击注销会删除cookie
    }

    //定义认证规则 也就是定义用户和他对应的角色
    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //定义认证的角色 inMemoryAuthentication是从内存中 也可以从数据库中查出来  为用户分配角色
        auth.inMemoryAuthentication().withUser("root").password("root").roles("Vip1", "Vip2", "Vip3")
                .and().withUser("lisi").password("123456").roles("Vip1")
        ;
    }
}

