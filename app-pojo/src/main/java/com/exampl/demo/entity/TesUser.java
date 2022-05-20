package com.exampl.demo.entity;
import lombok.Data;
import java.io.Serializable;

@Data
public class TesUser implements Serializable {
    private Integer id;
    private String name;
    private Integer age;

}
