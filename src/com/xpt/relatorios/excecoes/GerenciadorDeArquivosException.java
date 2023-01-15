package com.xpt.relatorios.excecoes;

public class GerenciadorDeArquivosException extends Exception {

    private static final long serialVersionUID = 8174935455081332828L;

    public GerenciadorDeArquivosException(final String menssagem, final Throwable causa) {
        super(menssagem, causa);
    }
}
