package io.github.jx2lee.gettingstarted.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class GettingStartedMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(GettingStartedMvcApplication.class, args);
    }

}
