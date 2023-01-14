package com.xpt.relatorios.src.main.arquivos;

import com.xpt.relatorios.src.main.excecoes.GerenciadorDeArquivosException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeArquivosCsv {

    private final static String CAMINHO = "resources";

    private final String[] cabecalho;

    private String separador = ",";

    public GerenciadorDeArquivosCsv(final String cabecalho) {
        this.cabecalho = cabecalho.split(separador);
    }

    public GerenciadorDeArquivosCsv(final String cabecalho, final String separador) {

        this.cabecalho = cabecalho.split(separador);
        this.separador = separador;
    }

    public <T> List<T> lerArquivo(final String nomeArquivo, final Class<T> classe) throws GerenciadorDeArquivosException {

        final List<T> objetos = new ArrayList<>();
        final String arquivo = String.format("%s/%s", CAMINHO, nomeArquivo);

        try (final BufferedReader bufferReader = new BufferedReader(new FileReader(arquivo))) {

            bufferReader.readLine();

            String line = bufferReader.readLine();

            while (line != null) {

                final String[] valores = line.split(separador);

                final T objeto = classe.getDeclaredConstructor().newInstance();

                for (int i = 0; i < valores.length; i++) {

                    final String cabecalhoItem = cabecalho[i];
                    final String valor = valores[i];

                    final Method setter = objeto.getClass().getDeclaredMethod(pegarSetter(cabecalhoItem), String.class);
                    setter.invoke(objeto, valor);
                }

                objetos.add(objeto);
                line = bufferReader.readLine();
            }
        } catch (final Exception e) {
            throw new GerenciadorDeArquivosException(String.format("Erro ao ler arquivo: [%s].", arquivo), e);
        }

        return objetos;
    }

    private String pegarSetter(final String campo) {
        return "set" + campo.substring(0, 1).toUpperCase() + campo.substring(1);
    }
}
