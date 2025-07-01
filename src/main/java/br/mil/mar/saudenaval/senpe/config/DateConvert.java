package br.mil.mar.saudenaval.senpe.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConvert {

        public static LocalDate parse(String date){
            LocalDate parsed;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            try {
                parsed = LocalDate.parse(date, formatter);
               // System.out.println("Data convertida: " + date);
            } catch (DateTimeParseException e) {
                throw new RuntimeException(e);
               // System.out.println("Erro ao converter a data: " + e.getMessage());
            }
            return parsed;
        }

}
