package com.xpt.relatorios.src.main.modelos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {

    public static final String CABECALHO = "empresa;mes;ano;valor;dataEmissao;nota";

    private static final DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    String empresa;

    int mes;

    int ano;

    BigDecimal valor;

    LocalDate dataEmissao;

    int nota;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(final String empresa) {
        this.empresa = empresa;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(final int mes) {
        this.mes = mes;
    }

    public void setMes(final String mes) {
        this.mes = Integer.parseInt(mes);
    }

    public int getAno() {
        return ano;
    }

    public void setAno(final int ano) {
        this.ano = ano;
    }

    public void setAno(final String ano) {
        this.ano = Integer.parseInt(ano);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(final BigDecimal valor) {
        this.valor = valor;
    }

    public void setValor(final String valor) {
        this.valor = BigDecimal.valueOf(Double.parseDouble(valor));
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(final LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setDataEmissao(final String dataEmissao) {
        this.dataEmissao = LocalDate.parse(dataEmissao, dataFormato);
    }

    public int getNota() {
        return nota;
    }

    public void setNota(final int nota) {
        this.nota = nota;
    }

    public void setNota(final String nota) {
        this.nota = Integer.parseInt(nota);
    }

    @Override
    public String toString() {

        return String.format("%s;%s;%s;%s;%s;%s", empresa, mes, ano, new DecimalFormat("#0.##").format(valor), dataEmissao.format(dataFormato), nota);
    }
}
