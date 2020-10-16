package io.github.nrodrigoc;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;

@Configuration
@Development // Anotation personalizada que herda as configurações da classe Anotation
//@Profile("development") // Anotation utilizada pra rodar as configurações apenas no perfil escolhido
public class MyConfiguration {


    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("RODANDO A CONFIG DE DESENVOLVIMENTO");
        };
    }


    @Bean(name = "applicationName")
    public String applicationName() {
        return "Sistema de Vendas";
    }


}
