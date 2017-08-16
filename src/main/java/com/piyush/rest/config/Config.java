package com.piyush.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/**
 * 
 * @author Piyush Chugh
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.piyush.rest")
public class Config {

}
