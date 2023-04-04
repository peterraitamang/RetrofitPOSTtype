package com.example.retrofitpost;

import java.util.HashMap;
import java.util.Map;

public class SignUpResponse {

    String success;
    String message;
    Integer userid;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


}
