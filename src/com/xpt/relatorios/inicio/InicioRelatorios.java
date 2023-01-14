package com.xpt.relatorios.inicio;

import com.xpt.relatorios.arquivos.GerenciadorDeArquivosCsv;
import com.xpt.relatorios.excecoes.GerenciadorDeArquivosException;
import com.xpt.relatorios.modelos.Faturamento;
import com.xpt.relatorios.modelos.Nota;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InicioRelatorios {

    private static final Logger LOGGER = Logger.getLogger(InicioRelatorios.class.getName());

    private static final String SEPARADOR = ";";

    public void gerarRelatorios() throws GerenciadorDeArquivosException {

        final GerenciadorDeArquivosCsv gerenciadorDeArquivosCsv = new GerenciadorDeArquivosCsv(Nota.CABECALHO, SEPARADOR);

        final List<Nota> notas = gerenciadorDeArquivosCsv.lerArquivo("nota.txt", Nota.class);

        final Map<String, Map<Integer, BigDecimal>> valorAnualPorEmpresa = pegarValorAnualPorEmpresa(notas);

        loggarValorAnualPorEmpresa(valorAnualPorEmpresa);

        gerenciadorDeArquivosCsv.setCabecalho(Faturamento.CABECALHO);

        final List<Faturamento> faturamentos = gerenciadorDeArquivosCsv.lerArquivo("faturamento.txt", Faturamento.class);

        final Map<String, Map<Integer, List<Faturamento>>> faturamentoAnualPorEmpresa = faturamentos.parallelStream()
                .collect(
                        Collectors.groupingBy(
                                Faturamento::getEmpresa, Collectors.groupingBy(
                                        Faturamento::getAno
                                )
                        )
                );

        LOGGER.info("Valor anual pago por parcela por empresa: ");
        for (final var empresa : faturamentoAnualPorEmpresa.entrySet()) {
            for (final var ano : empresa.getValue().entrySet()) {

                // ano.getValue().stream().reduce();
            }
        }

        System.out.println();
    }

    private static Map<String, Map<Integer, BigDecimal>> pegarValorAnualPorEmpresa(List<Nota> notas) {

        return notas.parallelStream()
                .collect(
                        Collectors.groupingBy(
                                Nota::getEmpresa, Collectors.toMap(
                                        Nota::getAno, Nota::getValor,
                                        BigDecimal::add)));
    }

    private static void loggarValorAnualPorEmpresa(Map<String, Map<Integer, BigDecimal>> valorAnualPorEmpresa) {

        LOGGER.info("Valor anual pago por empresa: ");
        for (final var empresa : valorAnualPorEmpresa.entrySet()) {
            for (final var ano : empresa.getValue().entrySet()) {
                LOGGER.info(String.format("Empresa %s: %s, %s", empresa.getKey(), ano.getKey(), ano.getValue()));
            }
        }
    }
}
