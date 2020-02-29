package com.java.test;

import java.util.Objects;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-02-29 12:30
 */
public class Generate {
    private String field_a;

    public String getField_a() {
        return field_a;
    }

    public void setField_a(String field_a) {
        this.field_a = field_a;
    }

    @Override
    public String toString() {
        return "Generate{" +
                "field_a='" + field_a + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Generate generate = (Generate) o;
        return field_a.equals(generate.field_a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field_a);
    }
}
