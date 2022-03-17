package io.github.jx2lee.gettingstarted.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MvcPart1Application {

    public static void main(String[] args) {
        SpringApplication.run(MvcPart1Application.class, args);
    }

}
