package customization;

import io.avaje.http.client.HttpException;
import io.avaje.http.client.RetryHandler;

import java.net.ConnectException;
import java.net.http.HttpResponse;

public class CustomRetry implements RetryHandler {

    private static final int MAX_RETRIES = 2;

    @Override
    public boolean isRetry(int retryCount, HttpResponse<?> response) {
        final var code = response.statusCode();

        return retryCount < MAX_RETRIES && code > 400;
    }

    @Override
    public boolean isExceptionRetry(int retryCount, HttpException response) {
        // Unwrap the exception
        final var cause = response.getCause();
        if (retryCount >= MAX_RETRIES) {
            return false;
        }

        return cause instanceof ConnectException;
    }

}
