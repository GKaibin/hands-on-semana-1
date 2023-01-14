package com.xpt.relatorios;

import com.xpt.relatorios.inicio.InicioRelatorios;
import com.xpt.relatorios.excecoes.GerenciadorDeArquivosException;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws GerenciadorDeArquivosException {

        LOGGER.info("Aplicação iniciada.");

        final InicioRelatorios inicioRelatorios = new InicioRelatorios();
        inicioRelatorios.gerarRelatorios();

        LOGGER.info("Aplicação encerrada.");
    }
}
