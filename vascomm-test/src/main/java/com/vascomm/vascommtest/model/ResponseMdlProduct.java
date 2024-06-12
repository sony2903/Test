package com.vascomm.vascommtest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMdlProduct {
    @JsonIgnore
    public static String SUCCESS = "Success";

    private String code;
    private String message;
    private Mst_Product data;

}
