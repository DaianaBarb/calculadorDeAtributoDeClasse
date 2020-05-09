package com.challenge.desafio;
import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object obj) {

        return Stream.of(obj.getClass().getDeclaredFields()) // pegando os atributos
                .filter(field -> field.isAnnotationPresent(Somar.class)) //se tiver a anotação somar // vai entrar no metodo isBigdecimal e vai retornar o atributo e vai somar
                .map(field -> isBigDecimal(field, obj))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal subtrair(Object obj) {

        return Stream.of(obj.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Subtrair.class))
                .map(field -> isBigDecimal(field, obj))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal totalizar(Object obj) {
        //subtrai do somar o subtrair
        return somar(obj).subtract(subtrair(obj));
    }

    private BigDecimal isBigDecimal(Field field, Object obj) {
        try { // field e igual aos atributos. esse metodo recebe o atributo e o objeto
            if (BigDecimal.class.isAssignableFrom(field.getType())) {// verifica se o atributo e do tipo bigdecimal
                field.setAccessible(true);// em tempo de execução ele acessa um atributo privado. e pega o valor // desse atributo anotado pega o valor e retorna um big decimal
                return (BigDecimal) field.get(obj); // ele retorna um objeto por isso o casting
            } else {
                return BigDecimal.ZERO;
            }
        } catch (IllegalAccessException e) {
            return BigDecimal.ZERO;
        }
    }

}