import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class HttpRequestUtility {
    private static Logger Log = LoggerFactory.getLogger(HttpRequestUtility.class);

    public HashMap<String, Object> cookies;
    public RequestSpecification reqSpec;

    public HttpRequestUtility(String baseURL) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseURL);
        reqSpec = builder.build();
    }

    public Response sendGetRequest(String url, String contentType, HashMap<String, Object> paramMap,
                                   HashMap<String, Object> header, HashMap<String, Object> cookies) {
        RequestSpecification reqSpec = constructRequestSpecification(contentType, paramMap, header, "",
                cookies);
        Response res = null;
        res = reqSpec.get(url);
        return res;
    }

    public Response sendPostRequest(String url, String contentType, HashMap<String, Object> paramMap,
                                    HashMap<String, Object> header, String jsonBody, HashMap<String, Object> cookies) {
        RequestSpecification reqSpec = constructRequestSpecification(contentType, paramMap, header, jsonBody,
                cookies);

        Response res = null;
        res = reqSpec.post(url);
        return res;
    }

    public Response sendPostRequest(String url, HashMap<String, Object> header, String jsonBody,
                                    HashMap<String, Object> cookies) {
        RequestSpecification request = constructRequestSpecification(null, null, header, jsonBody, cookies);

        Response res = request.post(url);
        return res;
    }

    public Response sendPutRequest(String url,
                                   HashMap<String, Object> header, String jsonBody) {
        RequestSpecification reqSpec = constructRequestSpecification("application/json", null, header, jsonBody,
                cookies);
        Response res = null;
        res = reqSpec.put(url);
        return res;
    }

    public Response sendDeleteRequest(String url, String contentType, HashMap<String, Object> paramMap,
                                      HashMap<String, Object> header, String jsonBody, HashMap<String, Object> cookies) {
        RequestSpecification reqSpec = constructRequestSpecification(contentType, paramMap, header, jsonBody,
                cookies);
        Response res = null;
        res = reqSpec.delete(url);
        return res;
    }

    private RequestSpecification constructRequestSpecification(String contentType,
                                                               HashMap<String, Object> paramMap, HashMap<String, Object> header, String jsonBody,
                                                               HashMap<String, Object> cookies) {
        RequestSpecification request = RestAssured.given(reqSpec);
        if (contentType != null) {
            request = request.contentType(contentType);
        }
        if (cookies != null && !cookies.isEmpty()) {
            request = request.cookies(cookies);
        }
        if (header != null && !header.isEmpty()) {
            request = request.headers(header);
        }
        if (paramMap != null && !paramMap.isEmpty()) {
            request = request.formParams(paramMap);
        }
        if (jsonBody != null) {
            request = request.body(jsonBody);
        }
        return request;
    }
}