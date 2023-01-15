package com.xpt.relatorios.utilidades;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ConversorTipos {

    public static final DateTimeFormatter DATA_FORMATO = DateTimeFormatter.ofPattern("dd/MM/yy");

    public static final DecimalFormat DECIMAL_FORMATO = new DecimalFormat("#0.##");

    private ConversorTipos() {

    }

    public static BigDecimal converterParaBigDecimal(final String valor) throws ParseException {

        final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());

        return BigDecimal.valueOf(numberFormat.parse(valor).doubleValue());
    }
}
