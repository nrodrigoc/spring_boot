package io.github.nrodrigoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // Fala que a anotation só pode ser utilizada em um campo
@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Qualifier("gato")
public @interface Gato {
}
