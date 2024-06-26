package com.abnamro.project.recipe.model;

import java.util.Objects;
import lombok.Getter;

@Getter
public class GenericResponse {

    private final String message;

    public GenericResponse(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericResponse that = (GenericResponse) o;

        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
