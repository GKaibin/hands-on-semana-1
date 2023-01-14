package com.xpt.relatorios.src.main;

import com.xpt.relatorios.src.main.excecoes.GerenciadorDeArquivosException;
import com.xpt.relatorios.src.main.inicio.InicioRelatorios;

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
