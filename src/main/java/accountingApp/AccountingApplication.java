package accountingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class
 */
@SpringBootApplication
public class AccountingApplication implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/",
            "classpath:/static/js/", "classpath:/js/",
            "classpath:/resources/static/js/", "classpath:/resources/static/"
    };

    public static void main(String[] args) {
        SpringApplication.run(AccountingApplication.class, args);
        try {
            // Создаем объект ProcessBuilder с командой, которую нужно выполнить
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "node D:\\JAVA\\REPOSITORY\\AccApp\\frontend\\src\\index.js");

            // Устанавливаем рабочий каталог для команды
            processBuilder.directory(new File("C:\\"));

            // Запускаем процесс
            Process process = processBuilder.start();

            // Читаем вывод команды
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println();
            }

            // Ждем завершения процесса
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }






}
