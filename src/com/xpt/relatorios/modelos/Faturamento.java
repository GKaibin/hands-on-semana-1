package com.xpt.relatorios.modelos;

import com.xpt.relatorios.utilidades.ConversorTipos;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

public class Faturamento {

    public static final String CABECALHO = "empresa;mes;ano;dataParcela1;valorParcela1;dataParcela2;valorParcela2;dataParcela3;valorParcela3";

    String empresa;

    int mes;

    int ano;

    LocalDate dataParcela1;

    BigDecimal valorParcela1;

    LocalDate dataParcela2;

    BigDecimal valorParcela2;

    LocalDate dataParcela3;

    BigDecimal valorParcela3;

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

    public LocalDate getDataParcela1() {
        return dataParcela1;
    }

    public void setDataParcela1(final LocalDate dataParcela1) {
        this.dataParcela1 = dataParcela1;
    }

    public void setDataParcela1(final String dataParcela1) {
        this.dataParcela1 = LocalDate.parse(dataParcela1, ConversorTipos.DATA_FORMATO);
    }

    public BigDecimal getValorParcela1() {
        return valorParcela1;
    }

    public void setValorParcela1(final BigDecimal valorParcela1) {
        this.valorParcela1 = valorParcela1;
    }

    public void setValorParcela1(final String valorParcela1) throws ParseException {
        this.valorParcela1 = ConversorTipos.converterParaBigDecimal(valorParcela1);
    }

    public LocalDate getDataParcela2() {
        return dataParcela2;
    }

    public void setDataParcela2(final LocalDate dataParcela2) {
        this.dataParcela2 = dataParcela2;
    }

    public void setDataParcela2(final String dataParcela2) {
        this.dataParcela2 = LocalDate.parse(dataParcela2, ConversorTipos.DATA_FORMATO);
    }

    public BigDecimal getValorParcela2() {
        return valorParcela2;
    }

    public void setValorParcela2(final BigDecimal valorParcela2) {
        this.valorParcela2 = valorParcela2;
    }

    public void setValorParcela2(final String valorParcela2) throws ParseException {
        this.valorParcela2 = ConversorTipos.converterParaBigDecimal(valorParcela2);
    }

    public LocalDate getDataParcela3() {
        return dataParcela3;
    }

    public void setDataParcela3(final LocalDate dataParcela3) {
        this.dataParcela3 = dataParcela3;
    }

    public void setDataParcela3(final String dataParcela3) {
        this.dataParcela3 = LocalDate.parse(dataParcela3, ConversorTipos.DATA_FORMATO);
    }

    public BigDecimal getValorParcela3() {
        return valorParcela3;
    }

    public void setValorParcela3(final BigDecimal valorParcela3) {
        this.valorParcela3 = valorParcela3;
    }

    public void setValorParcela3(final String valorParcela3) throws ParseException {
        this.valorParcela3 = ConversorTipos.converterParaBigDecimal(valorParcela3);
    }

    @Override
    public String toString() {

        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s",
                empresa,
                mes,
                ano,
                dataParcela1.format(ConversorTipos.DATA_FORMATO),
                ConversorTipos.DECIMAL_FORMATO.format(valorParcela1),
                dataParcela2.format(ConversorTipos.DATA_FORMATO),
                ConversorTipos.DECIMAL_FORMATO.format(valorParcela2),
                dataParcela3.format(ConversorTipos.DATA_FORMATO),
                ConversorTipos.DECIMAL_FORMATO.format(valorParcela3));
    }
}
