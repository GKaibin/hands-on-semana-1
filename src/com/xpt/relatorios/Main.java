package com.xpt.relatorios;

import com.xpt.relatorios.configuracoes.ConfigAnosRelatorios;
import com.xpt.relatorios.inicio.InicioRelatorios;
import com.xpt.relatorios.excecoes.GerenciadorDeArquivosException;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(final String[] args) throws GerenciadorDeArquivosException {

        LOGGER.info("Aplicação iniciada.");

        final var configAnosRelatorios = new ConfigAnosRelatorios();

        final var entrada = new Scanner(System.in);
        LOGGER.info("Digite \"sim\" caso deseje filtrar por ano:");
        final String ativarFiltro = entrada.nextLine();
        if ("sim".equalsIgnoreCase(ativarFiltro.trim())) {

            configAnosRelatorios.setFiltroAtivo(true);
            LOGGER.info("Digite os anos dos relatórios desejados separados por vírgula:");
            configAnosRelatorios.setAnos(entrada.nextLine());
        }
        entrada.close();

        final InicioRelatorios inicioRelatorios = new InicioRelatorios();
        inicioRelatorios.gerarRelatorios(configAnosRelatorios);

        LOGGER.info("Aplicação encerrada.");
    }
}
