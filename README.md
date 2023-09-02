# springboot-security-3.1

Earlier we have WebSecurityConfigureAdaptor to override the below methods :

* configure(AuthenticationManagerBuilder auth)
* configure(HttpSecurity httpSecurity)

But now in Spring-boot:3.0 We need to create bean for this use case those are as below :

Below method will add the multiple user in our application
```
@Bean
public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
UserDetails admin = User.withUsername("Admin").password(passwordEncoder().encode("demo123")).roles("ADMIN").build();
UserDetails user = User.withUsername("User").password(passwordEncoder().encode("xyz123")).roles("USER").build();
return new InMemoryUserDetailsManager(admin,user);
}
```

And to specifiy the Authorization we need to create SecurityFilterChain bean where we specify the url based authentication
like below : 

```
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/products/welcome", "/products/new").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/products/**").authenticated()
                .and()
                .formLogin().and().build();

    }
```

## Stay tuned for further updates
