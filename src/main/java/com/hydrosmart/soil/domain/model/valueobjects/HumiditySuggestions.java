package com.hydrosmart.soil.domain.model.valueobjects;

import java.util.Map;

public final class HumiditySuggestions {
    private HumiditySuggestions(){}
    public static final Map<HumidityStatusList, String> SUGGESTIONS = Map.of(
            HumidityStatusList.FAVORABLE, "La humedad es adecuada para el cultivo. No se requiere acción.",
            HumidityStatusList.SLIGHTLY_UNFAVORABLE_UNDER, "Humedad levemente por debajo de lo adecuado. Se recomienda aumentar la frecuencia de riego",
            HumidityStatusList.SLIGHTLY_UNFAVORABLE_OVER, "Humedad levemente por encima de lo adecuado. Se recomienda reducir la frecuencia de riego",
            HumidityStatusList.UNFAVORABLE_UNDER, "Humedad por debajo de lo adecuado. Se sugiere aumentar considerablemente la frecuencia de riego",
            HumidityStatusList.UNFAVORABLE_OVER, "Humedad por encima de lo adecuado. Se sugiere reducir considerablemente la frecuencia de riego",
            HumidityStatusList.FLOODED, "El suelo está inundado. Se requiere atención manual para eliminar el exceso de agua",
            HumidityStatusList.DRY, "El suelo está seco. Se requiere regar inmediatamente para no perder los cultivos"
    );
}
