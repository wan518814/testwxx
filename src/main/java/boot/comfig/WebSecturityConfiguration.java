package boot.comfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import sun.nio.cs.ext.DoubleByte;

/**
 * @author wanglufei
 * @description: SpringSecurity配置类
 * @date 2022/4/11/6:31 PM
 */
//@Configuration
//@EnableWebSecurity
public class WebSecturityConfiguration{/* extends WebSecurityConfigurerAdapter {
//
//    /**
//     * 自定义加密逻辑
//     *
//     * @return org.springframework.security.crypto.password.PasswordEncoder
//     * @author wanglufei
//     * @date 2022/4/11 6:32 PM
//     */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 自定义web相关的属性
//     *
//     * @param
//     * @author wanglufei.
//     * @date 2022/4/11 7:30 PM
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("ADMIN")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
//
//    }
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //禁用csrf保护
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .antMatchers("/hello", "/hellopost","/redis/**","/**").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin().and().httpBasic();
//
//    }
//
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}

