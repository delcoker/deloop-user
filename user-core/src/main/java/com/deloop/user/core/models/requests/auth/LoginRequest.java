package com.deloop.user.core.models.requests.auth;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class LoginRequest implements Serializable {

    @Builder.Default
    @ApiParam(required = true, value = "delcoker@gmail.com", example = "delcoker@gmail.com")
    private String email = "";

    @Builder.Default
    @ApiParam(required = true, example = "123")
    private String password = "";

    @Builder.Default
    private String username = "";
    //
    @Builder.Default
    @ApiParam(value = "false", allowEmptyValue = true)
    private Boolean rememberMe = false;

    public String toRequestParam() {
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("email", email));
        pairs.add(new BasicNameValuePair("password", password));
        pairs.add(new BasicNameValuePair("rememberMe", String.valueOf(rememberMe)));
        pairs.add(new BasicNameValuePair("username", username));

        return URLEncodedUtils.format(pairs, StandardCharsets.UTF_8);
    }

    @ApiParam(hidden = true)
    public boolean isValid() {
        if (StringUtils.isEmpty(email) || email.length() < 3) {
            return false;
        }
        if (StringUtils.isEmpty(password) || password.length() < 3) {
            return false;
        }
        return true;
    }
}
