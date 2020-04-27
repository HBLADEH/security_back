package com.pjboy.security_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@MapperScan("com.pjboy.security_back.dao")
@SpringBootApplication
public class SecurityBackApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityBackApplication.class, args);
  }

}
