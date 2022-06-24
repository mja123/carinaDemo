package com.qaprosoft.carina.demo.myImplementation.api.weather;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class GetCurrentWeatherTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    public void getCurrentWeatherTest() {
        CurrentWeatherData api = new CurrentWeatherData();

        api.replaceUrlPlaceholder("lat", "35");
        api.replaceUrlPlaceholder("lon", "139");
        api.replaceUrlPlaceholder("key", "37089048ef111a660fec1c2b5f741d30");

        api.callAPI();

        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/weather/_get/rs.schema");
        api.callAPIExpectSuccess();
    }

    @Test()
    public void getCurrentWeatherWithInvalidApiKey() {
        GetCurrentWeatherErrors api = new GetCurrentWeatherErrors();

        api.replaceUrlPlaceholder("lat", "35");
        api.replaceUrlPlaceholder("lon", "139");
        api.replaceUrlPlaceholder("key", "37089048ef111a660fec1c2b5f741dwe");

        api.callAPI();

        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/weather/_getError/rs.schema");
        api.callAPIExpectSuccess();

    }
}
