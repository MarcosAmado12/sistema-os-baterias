package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Conversor {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static double lerDecimal(String texto, String nomeCampo) {
        try {
            double valor = Double.parseDouble(texto.trim().replace(",", "."));
            if (valor < 0) {
                throw new IllegalArgumentException("O campo " + nomeCampo + " não pode ser negativo.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Informe um número válido no campo " + nomeCampo + ".");
        }
    }

    public static int lerInteiro(String texto, String nomeCampo) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Informe um número inteiro no campo " + nomeCampo + ".");
        }
    }

    public static LocalDate lerData(String texto, String nomeCampo) {
        try {
            return LocalDate.parse(texto.trim(), FORMATO_DATA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Informe uma data no formato dd/mm/aaaa no campo " + nomeCampo + ".");
        }
    }

    public static String formatarValor(double valor) {
        return String.format("R$ %.2f", valor).replace(".", ",");
    }

    public static String formatarData(LocalDate data) {
        if (data == null) {
            return "";
        }
        return data.format(FORMATO_DATA);
    }
}
