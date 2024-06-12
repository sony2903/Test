package com.vascomm.vascommtest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMdl {
    @JsonIgnore
    public static String SUCCESS = "Success";

    private String code;
    private String message;
    private Mst_Employee data;

}
