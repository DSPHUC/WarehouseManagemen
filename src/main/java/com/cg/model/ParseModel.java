package com.cg.model;

public interface ParseModel<T> {
    T parse(String line);
}