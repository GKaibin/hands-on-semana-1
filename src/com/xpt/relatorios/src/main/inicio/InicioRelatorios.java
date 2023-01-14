package com.xpt.relatorios.src.main.inicio;

import com.xpt.relatorios.src.main.arquivos.GerenciadorDeArquivosCsv;
import com.xpt.relatorios.src.main.excecoes.GerenciadorDeArquivosException;
import com.xpt.relatorios.src.main.modelos.Nota;

import java.util.List;

public class InicioRelatorios {

    private static final String SEPARADOR = ";";

    public void gerarRelatorios() throws GerenciadorDeArquivosException {

        final GerenciadorDeArquivosCsv gerenciadorDeArquivosNota = new GerenciadorDeArquivosCsv(Nota.CABECALHO, SEPARADOR);

        final List<Nota> notas = gerenciadorDeArquivosNota.lerArquivo("nota.txt", Nota.class);
    }
}
