package com.challenge.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD) // onde eu posso colocar esta anotation? fileld no caso sao os campos ou seja atributos
@Retention(RUNTIME) // runtime significa tempo de execução
public @interface Somar {
}
