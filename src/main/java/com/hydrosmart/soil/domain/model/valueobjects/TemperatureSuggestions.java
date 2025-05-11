package com.hydrosmart.soil.domain.model.valueobjects;

import java.util.Map;

public final class TemperatureSuggestions {
    private TemperatureSuggestions(){}

    public static final Map<TemperatureStatusList, String> SUGGESTIONS = Map.of(
            TemperatureStatusList.FAVORABLE, "La temperatura es adecuada para el cultivo. No se requiere acción.",
            TemperatureStatusList.SLIGHTLY_UNFAVORABLE_UNDER, "Temperatura ligeramente por debajo de lo adecuado. Se recomienda exponer los cultivos más al sol",
            TemperatureStatusList.SLIGHTLY_UNFAVORABLE_OVER, "Temperatura ligeramente por encima de lo adecuado. Se recomienda agregar más sobra a los cultivos",
            TemperatureStatusList.UNFAVORABLE_UNDER, "Temperatura por debajo de lo adecuado. Se recomienda exponer los cultivos más al sol y verificar la frecuencia del regado",
            TemperatureStatusList.UNFAVORABLE_OVER, "Temperatura por encima de lo adecuado. Se recomienda agregar más sobra a los cultivos y aumentar ligeramente la frecuencia de regado",
            TemperatureStatusList.BURNING, "El suelo está en llamas. Mantenga la calma e intente apagar el fuego. Es posible que los cultivos se hayan perdido",
            TemperatureStatusList.FREEZING, "El suelo está muy por debajo de lo adecuado. Intente exponer los cultivos más al sol y regar con agua tibia para nivelar la temperatura"
    );
}
