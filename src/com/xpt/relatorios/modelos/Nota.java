package com.xpt.relatorios.modelos;

import com.xpt.relatorios.utilidades.ConversorTipos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;

public class Nota {

    public static final String CABECALHO = "empresa;mes;ano;valor;dataEmissao;nota";

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

    public void setValor(final String valor) throws ParseException {
        this.valor = ConversorTipos.converterParaBigDecimal(valor);
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(final LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setDataEmissao(final String dataEmissao) {
        this.dataEmissao = LocalDate.parse(dataEmissao, ConversorTipos.DATA_FORMATO);
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

        return String.format("%s;%s;%s;%s;%s;%s", empresa, mes, ano, new DecimalFormat("#0.##").format(valor), dataEmissao.format(ConversorTipos.DATA_FORMATO), nota);
    }
}
