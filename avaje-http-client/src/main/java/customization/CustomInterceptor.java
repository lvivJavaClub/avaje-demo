package customization;

import io.avaje.http.client.HttpClientRequest;
import io.avaje.http.client.RequestIntercept;

import java.net.http.HttpResponse;

public class CustomInterceptor implements RequestIntercept {

    private static final String REQUEST_SENT = "REQUEST_SENT";

    long requestMillis;

    @Override
    public void beforeRequest(HttpClientRequest request) {
        request.setAttribute(REQUEST_SENT, System.currentTimeMillis()).label();
    }

    @Override
    public void afterResponse(HttpResponse<?> response, HttpClientRequest request) {
        final long start = request.getAttribute(REQUEST_SENT);
        requestMillis = System.currentTimeMillis() - start;
    }

    public long getRequestMillis() { return requestMillis; }
}
