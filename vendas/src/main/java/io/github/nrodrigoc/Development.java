package io.github.nrodrigoc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Criando uma anotation personalizada

@Target(ElementType.TYPE) // Fala que a anotation só pode ser utilizada numa classe
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Profile("development")
public @interface Development {

}
