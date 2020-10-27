package io.github.nrodrigoc.api;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String mensagemErro) {
        this.errors = Arrays.asList(mensagemErro);
    }

}
