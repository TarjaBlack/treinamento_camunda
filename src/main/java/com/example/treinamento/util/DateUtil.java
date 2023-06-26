package com.example.treinamento.util;

import java.util.Objects;

public class DateUtil {

    private DateUtil(){
    }


    public static String millecondsBeautiful(Long milleconds){

        if (Objects.isNull(milleconds)){
            milleconds = 0l;
        }

        Long segundos = milleconds/1000;
        Long resDias = segundos%5184000;
        Long resHoras = resDias%216000;
        Long resMinutos = resHoras%60;

        return "dias: ".concat(String.valueOf(segundos/5184000))
                .concat(" horas: ").concat(String.valueOf(resDias/216000))
                .concat(" minutos: ").concat(String.valueOf(resHoras/60))
                .concat(" segundos: ").concat(String.valueOf(resMinutos/60));
    }
}
