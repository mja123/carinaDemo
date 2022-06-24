package com.qaprosoft.carina.demo.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

//@Endpoint(url = "${base_url}/users", methodType = HttpMethodType.GET)

@Endpoint(url = "${base_url}/data/2.5/weather?lat=33.44&lon=-94.04&appid=ebcbce76c2e1c2539d1c46431a4455f6", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/users/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetUserMethods extends AbstractApiMethodV2 {

    public GetUserMethods() {
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}