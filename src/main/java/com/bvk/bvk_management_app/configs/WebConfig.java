package com.bvk.bvk_management_app.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Menentukan endpoint mana yang diizinkan
				.allowedOrigins("*") // Mengizinkan semua origin
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metode yang diizinkan
				.allowedHeaders("*") // Mengizinkan semua header
				.exposedHeaders("Authorization"); // Jika Anda ingin mengekspos header tertentu
	}
}
