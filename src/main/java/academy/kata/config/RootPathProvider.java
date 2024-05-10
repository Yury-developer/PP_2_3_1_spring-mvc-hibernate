package academy.kata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;


/*
RootPathProvider реализует интерфейс ServletContextAware,
чтобы получить доступ к ServletContext при запуске приложения.
Метод getRootPath() возвращает абсолютный путь к корню веб-приложения.
 */
@Component
public class RootPathProvider {

    private final ResourceLoader resourceLoader;

    public RootPathProvider(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getRootPath() {
        Resource resource = resourceLoader.getResource("/");
        try {
            return resource.getURL().getPath();
        } catch (IOException e) {
            throw new RuntimeException("Failed to get root path", e);
        }
    }
}