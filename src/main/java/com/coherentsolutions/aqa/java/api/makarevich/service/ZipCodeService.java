package com.coherentsolutions.aqa.java.api.makarevich.service;

import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;

import static com.coherentsolutions.aqa.java.api.makarevich.configuration.Configuration.*;
import static io.restassured.RestAssured.given;

public class ZipCodeService {
    @Step("Get zip codes")
    public ArrayList<String> getZipCodes() {
        return given()
                .header("Authorization", "Bearer " + TokenService.getInstance().getReadToken())
                .when()
                .get(API_REQUEST_URI + API_ZIPCODES_ENDPOINT)
                .getBody()
                .as(new TypeRef<ArrayList<String>>() {
                });
    }

    @Step("Add zip codes")
    public Response addZipCode(String... zipCode) {
        return given()
                .header("Authorization", "Bearer " + TokenService.getInstance().getWriteToken())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(zipCode)
                .when()
                .post(API_REQUEST_URI + API_ZIPCODES_EXPAND_ENDPOINT);
    }
}