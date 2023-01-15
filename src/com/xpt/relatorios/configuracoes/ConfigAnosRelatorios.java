package com.xpt.relatorios.configuracoes;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigAnosRelatorios {

    Set<Integer> anos;

    boolean filtroAtivo;

    public Set<Integer> getAnos() {
        return anos;
    }

    public void setAnos(final Set<Integer> anos) {
        this.anos = anos;
    }

    public void setAnos(final String anos) {
        this.anos = Arrays.stream(anos.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public void addAno(final Integer ano) {
        anos.add(ano);
    }

    public boolean filtroEstaAtivo() {
        return filtroAtivo;
    }

    public void setFiltroAtivo(final boolean filtroAtivo) {
        this.filtroAtivo = filtroAtivo;
    }
}
