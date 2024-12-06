package com.fisiunmsm.grupo3.comp.presentation.config.error;

public class ApiException extends RuntimeException {

    private final String titulo;
    private final String descripcion;

    public ApiException(String titulo, String descripcion) {
        super(titulo + ": " + descripcion);
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}