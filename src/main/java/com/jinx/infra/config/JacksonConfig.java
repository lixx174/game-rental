package com.jinx.infra.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author jinx
 */
@Configuration
public class JacksonConfig {

    public static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Bean
    public static ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build()
                .registerModule(new JavaTimeModule()
                        .addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER))
                        .addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER))
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATETIME_FORMATTER))
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_FORMATTER))
                )
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
