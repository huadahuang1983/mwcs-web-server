package com.reebake.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.feiniaojin.gracefulresponse.data.Response;
import com.feiniaojin.gracefulresponse.data.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Getter
@Setter
public class CustomResponseImpl implements Response {

    private String code;
    private String msg;

    private Object data = Collections.EMPTY_MAP;

    @Override
    public void setStatus(ResponseStatus statusLine) {
        this.code = statusLine.getCode();
        this.msg = statusLine.getMsg();
    }

    @Override
    @JsonIgnore
    public ResponseStatus getStatus() {
        return null;
    }

    @Override
    public void setPayload(Object payload) {
        this.data = payload;
    }

    @Override
    @JsonIgnore
    public Object getPayload() {
        return null;
    }

    @JsonProperty("message")
    public String getMsg() {
        return this.msg;
    }

    @JsonIgnore
    public String getCode() {
        return this.code;
    }

    @JsonProperty("code")
    public Integer getCodeNum() {
        return Integer.parseInt(this.code);
    }
}
