import io.avaje.http.api.AvajeJavalinPlugin;
import io.avaje.inject.BeanScope;
import io.javalin.Javalin;

import java.util.List;

public class JavalinApp {

    public static void main(String[] args) {
        final List<AvajeJavalinPlugin> webRoutes;
        try (var beanScope = BeanScope.builder().build()) {
            webRoutes = beanScope.list(AvajeJavalinPlugin.class);
        }
        Javalin app = Javalin.create(config -> webRoutes.forEach(config::registerPlugin))
                .start(8080);
    }
}
