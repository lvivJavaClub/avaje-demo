import customization.CustomInterceptor;
import customization.CustomRetry;
import entity.User;
import io.avaje.http.client.HttpClient;
import io.avaje.http.client.JsonbBodyAdapter;
import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ClientApp {

    public static void main(final String[] args) {
        final CustomInterceptor customInterceptor = new CustomInterceptor();
        final HttpClient httpClient = HttpClient.builder()
                .baseUrl("http://localhost:8080/users")
                .bodyAdapter(new JsonbBodyAdapter())
                .requestIntercept(customInterceptor)
                .retryHandler(new CustomRetry())
                .build();

        // Prepare data
        httpClient.request()
                .body(new User(1L, "Jon Doe"))
                .POST()
                .asVoid();
        httpClient.request()
                .body(new User(2L, "Jane Doe"))
                .POST()
                .asVoid();

        // Bean response
        final User user = httpClient.request()
                .path("1")
                .GET()
                .bean(User.class);
        log.info("User 1: {}/{}", user.id(), user.name());

        // Http response
        final HttpResponse<String> response = httpClient.request()
                .path(2)
                .GET()
                .asString();
        log.info("User 2: {}", response.body());

        // Async request
        httpClient.request()
                .path(1)
                .GET()
                .async()
                .asString()
                .whenComplete((httpResponse, throwable) -> {
                    if(throwable != null) {
                        log.error("Exception occurred: {}", throwable.getMessage());
                    } else {
                        log.info("Status code: {}. User 1 ({}) got in async mode",
                                httpResponse.statusCode(), httpResponse.body());
                    }
                })
                .join();
        log.info("Request duration: {}ms", customInterceptor.getRequestMillis());

        // Stream response
        final HttpResponse<List<User>> userList = httpClient.request()
                .GET()
                .asList(User.class);
        log.info("User list: {}", userList.body().stream()
                .map(u -> String.format("%d/%s", u.id(), u.name()))
                .collect(Collectors.joining(";")));

    }

}
