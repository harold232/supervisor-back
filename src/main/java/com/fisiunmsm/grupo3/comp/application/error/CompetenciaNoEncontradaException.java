package com.fisiunmsm.grupo3.comp.application.error;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fisiunmsm.grupo3.comp.presentation.config.error.ApiException;

public class CompetenciaNoEncontradaException extends ApiException {

    public CompetenciaNoEncontradaException(MessageSource mensajes, String codigo) {
        super(
                mensajes.getMessage("comp.err.no_encontrado.tit", null, LocaleContextHolder.getLocale()),
                mensajes.getMessage("comp.err.no_encontrado.desc", new String[] { codigo },
                        LocaleContextHolder.getLocale()));
    }
}