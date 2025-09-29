package com.jeyz9.spring_sqlite.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean // สร้าง Bean เพื่อกำหนดค่ากลางของ OpenAPI
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product API Documentation") // ชื่อ API
                        .version("1.0.0") // เวอร์ชัน
                        .description("API Documentation for managing products in SQLite database.") // คำอธิบาย
                );
    }
}
