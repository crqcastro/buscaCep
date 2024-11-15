package br.com.cesarcastro.buscacep.support;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Constants {
    public static final String MAPS_URL = "https://www.google.com/maps/search/{latitude}+{longitude}/";
}
