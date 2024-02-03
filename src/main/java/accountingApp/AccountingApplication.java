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

        /*Starting of index.js script,the part of React.js*/
        try {
            //Create a ProcessBuilder object with the command to be executed
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "node D:\\JAVA\\REPOSITORY\\AccApp\\frontend\\src\\index.js");

            // Задаем путь к исполняемому файлу mongod
            String mongoDBPath = "C:\\mongod.exe.lnk";

            //Set the working directory for the command
            processBuilder.directory(new File("C:\\"));

            // Создаем ProcessBuilder
            ProcessBuilder processBuilder2 = new ProcessBuilder("cmd.exe", "/c", mongoDBPath);

            // Запускаем процесс
            Process process2 = processBuilder2.start();

            //Start the process
            Process process = processBuilder.start();

            //Waiting for the process finishing
            int exitCode = process.waitFor();
            // Ждем, пока процесс не завершится
            int exitCode2 = process2.waitFor();
            System.out.println("Exit Code: " + exitCode);
            if (exitCode2 == 0) {
                System.out.println("mongod успешно запущен!");
            } else {
                System.out.println("Ошибка при запуске mongod: " + exitCode2);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }


}
