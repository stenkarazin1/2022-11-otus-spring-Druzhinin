package ru.otus.springboot.config;

public interface PropertyConfig {

    String getProperty( String property ) throws IllegalArgumentException;

}
