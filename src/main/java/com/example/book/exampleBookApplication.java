package com.example.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by michael on 2020/04/28.
 */
@Slf4j
@SpringBootApplication

public class exampleBookApplication {

    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    /**
     * dev:开发、qa:测试、pre:预发、prod:生产；为空则默认读取“application.yml”
     */
    private static final String PROFILE = "";

    private static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap();
        defProperties.put(SPRING_PROFILE_DEFAULT, PROFILE);
        app.setDefaultProperties(defProperties);
    }

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(exampleBookApplication.class);
        addDefaultProfile(app);

        Environment env = app.run(args).getEnvironment();
        String applicationName = env.getProperty("spring.application.name");
        String serverPort = env.getProperty("server.port");

        String configServerStatus = env.getProperty("spring.profiles.active");

        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();

            log.info("\n---------------------------------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://localhost:{}\n\t" +
                            "External: \thttp://{}:{}\n---------------------------------------------------------------------------------",
                    applicationName,
                    serverPort,
                    hostAddress, serverPort);

            log.info("\n---------------------------------------------------------------------------------\n\t" +
                            "Config Server: \t{}\n---------------------------------------------------------------------------------",
                    configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

