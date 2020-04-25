package com.pjboy.security_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan("com.pjboy.security_back.dao")
public class SecurityBackApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityBackApplication.class, args);
  }

}
