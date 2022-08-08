package com.elltor.security.ba.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    Integer code;
    String msg;
    String data;

    public String toJsonString() {
        return JsonUtils.writeAsString(this);
    }
}
