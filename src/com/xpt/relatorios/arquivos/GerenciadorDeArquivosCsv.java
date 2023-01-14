package com.xpt.relatorios.arquivos;

import com.xpt.relatorios.excecoes.GerenciadorDeArquivosException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GerenciadorDeArquivosCsv {

    private static final Logger LOGGER = Logger.getLogger(GerenciadorDeArquivosCsv.class.getName());

    private static final String CAMINHO = "resources/";

    private String[] cabecalho;

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
        final String arquivo = pegarArquivo(nomeArquivo);

        try (final BufferedReader bufferReader = new BufferedReader(new FileReader(arquivo))) {

            bufferReader.readLine();

            String line = bufferReader.readLine();

            while (line != null) {

                final String[] valores = line.split(separador);

                if (valores.length != cabecalho.length) {
                    LOGGER.warning("Número de valores da não correspondentes!");
                    line = bufferReader.readLine();
                    continue;
                }

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

    public void setCabecalho(final String cabecalho) {
        this.cabecalho = cabecalho.split(separador);
    }

    private String pegarArquivo(final String nomeArquivo) {
        return Objects.requireNonNull(getClass().getClassLoader().getResource(CAMINHO + nomeArquivo)).getFile();
    }

    private String pegarSetter(final String campo) {
        return "set" + campo.substring(0, 1).toUpperCase() + campo.substring(1);
    }
}
