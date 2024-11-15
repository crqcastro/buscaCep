package br.com.cesarcastro.buscacep.support.exceptions.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record FieldErrorDto(String key, String reason) implements Serializable, Comparable<FieldErrorDto> {

    @Override
    public int compareTo(@NotNull FieldErrorDto o) {
        return key.compareTo(o.key);
    }
}