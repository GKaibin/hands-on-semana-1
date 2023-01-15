package com.xpt.relatorios.inicio;

import com.xpt.relatorios.arquivos.GerenciadorDeArquivosCsv;
import com.xpt.relatorios.configuracoes.ConfigAnosRelatorios;
import com.xpt.relatorios.excecoes.GerenciadorDeArquivosException;
import com.xpt.relatorios.modelos.Faturamento;
import com.xpt.relatorios.modelos.Nota;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InicioRelatorios {

    private static final Logger LOGGER = Logger.getLogger(InicioRelatorios.class.getName());

    private static final String SEPARADOR = ";";

    public void gerarRelatorios(final ConfigAnosRelatorios configAnos) throws GerenciadorDeArquivosException {

        final GerenciadorDeArquivosCsv gerenciadorDeArquivosCsv = new GerenciadorDeArquivosCsv(Nota.CABECALHO, SEPARADOR);

        final List<Nota> notas = gerenciadorDeArquivosCsv.lerArquivo("nota.txt", Nota.class);

        final Map<String, Map<Integer, BigDecimal>> valorAnualPorEmpresa = pegarValorAnualPorEmpresa(notas, configAnos);

        loggarValorAnualPorEmpresa(valorAnualPorEmpresa);

        gerenciadorDeArquivosCsv.setCabecalho(Faturamento.CABECALHO);

        final List<Faturamento> faturamentos = gerenciadorDeArquivosCsv.lerArquivo("faturamento.txt", Faturamento.class);

        final Map<String, Map<Integer, List<Faturamento>>> faturamentoAnualPorEmpresa = pegarFaturamentoAnualDeParcelasPorEmpresa(faturamentos, configAnos);

        loggarValorAnualDeParcelasPorEmpresa(faturamentoAnualPorEmpresa);
    }

    private static Map<String, Map<Integer, BigDecimal>> pegarValorAnualPorEmpresa(final List<Nota> notas, final ConfigAnosRelatorios configAnos) {

        return notas.parallelStream()
                .filter(n ->  filtrarAnos(n, configAnos))
                .collect(
                        Collectors.groupingBy(
                                Nota::getEmpresa, Collectors.toMap(
                                        Nota::getAno, Nota::getValor,
                                        BigDecimal::add
                                )
                        )
                );
    }

    private static void loggarValorAnualPorEmpresa(final Map<String, Map<Integer, BigDecimal>> valorAnualPorEmpresa) {

        LOGGER.info("Valor anual pago por empresa: ");
        for (final var empresa : valorAnualPorEmpresa.entrySet()) {
            for (final var ano : empresa.getValue().entrySet()) {
                LOGGER.info(String.format("Empresa %s: %s, %s", empresa.getKey(), ano.getKey(), ano.getValue()));
            }
        }
    }

    private static Map<String, Map<Integer, List<Faturamento>>> pegarFaturamentoAnualDeParcelasPorEmpresa(final List<Faturamento> faturamentos, final ConfigAnosRelatorios configAnos) {

        return faturamentos.parallelStream()
                .filter(f -> filtrarAnos(f, configAnos))
                .collect(
                        Collectors.groupingBy(
                                Faturamento::getEmpresa, Collectors.groupingBy(
                                        Faturamento::getAno
                                )
                        )
                );
    }

    private static void loggarValorAnualDeParcelasPorEmpresa(final Map<String, Map<Integer, List<Faturamento>>> faturamentoAnualPorEmpresa) {

        LOGGER.info("Valor anual pago por parcela por empresa: ");
        for (final var empresa : faturamentoAnualPorEmpresa.entrySet()) {
            for (final var ano : empresa.getValue().entrySet()) {

                final var valorAnualParcela1 = ano.getValue().stream().
                        map(Faturamento::getValorParcela1)
                        .reduce(BigDecimal::add)
                        .orElseThrow();

                final var valorAnualParcela2 = ano.getValue().stream().
                        map(Faturamento::getValorParcela2)
                        .reduce(BigDecimal::add)
                        .orElseThrow();

                final var valorAnualParcela3 = ano.getValue().stream().
                        map(Faturamento::getValorParcela3)
                        .reduce(BigDecimal::add)
                        .orElseThrow();

                LOGGER.info(String.format("Empresa %s: %s, Total parcela 1: %s, Total parcela 2: %s, Total parcela 3: %s", empresa.getKey(), ano.getKey(), valorAnualParcela1, valorAnualParcela2, valorAnualParcela3));
            }
        }
    }

    private static boolean filtrarAnos(final Nota nota, final ConfigAnosRelatorios configAnos) {

        if (!configAnos.filtroEstaAtivo()) {
            return true;
        }
        return configAnos.getAnos().contains(nota.getAno());
    }

    private static boolean filtrarAnos(final Faturamento faturamento, final ConfigAnosRelatorios configAnos) {

        if (!configAnos.filtroEstaAtivo()) {
            return true;
        }
        return configAnos.getAnos().contains(faturamento.getAno());
    }
}
