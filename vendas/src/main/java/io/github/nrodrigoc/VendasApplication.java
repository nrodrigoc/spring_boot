package io.github.nrodrigoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @Cachorro
    private Animal animal;

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }

    @Bean(name = "executarAnimal")
    public CommandLineRunner executar() {
        return args -> this.animal.fazerBarulho();
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
