package com.qaprosoft.carina.demo.myImplementation.api.weather;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;


@Endpoint(url = "${base_url}/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${key}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/weather/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class CurrentWeatherData extends AbstractApiMethodV2 {

    public CurrentWeatherData() {
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
