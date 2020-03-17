package com.telmore.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    private final int id;
    private final String name;
    private final int age;

}