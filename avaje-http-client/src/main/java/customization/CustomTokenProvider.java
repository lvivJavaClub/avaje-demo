package customization;

import io.avaje.http.client.AuthToken;
import io.avaje.http.client.AuthTokenProvider;
import io.avaje.http.client.HttpClientRequest;

public class CustomTokenProvider implements AuthTokenProvider {

    @Override
    public AuthToken obtainToken(HttpClientRequest tokenRequest) {
//        MyAuthTokenResponse res = tokenRequest
//                .url("https://foo/v2/token")
//                .header("content-type", "application/json")
//                .body(authRequestAsJson())
//                .POST()
//                .bean(MyAuthTokenResponse.class);
//
//        Instant validUntil = Instant.now().plusSeconds(res.expiresIn()).minusSeconds(60);
//
//        return AuthToken.of(res.accessToken(), validUntil);
        return null;
    }

}