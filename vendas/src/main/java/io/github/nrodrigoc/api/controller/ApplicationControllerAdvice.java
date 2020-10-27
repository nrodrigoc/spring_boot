package io.github.nrodrigoc.api.controller;

import io.github.nrodrigoc.api.ApiErrors;
import io.github.nrodrigoc.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegociosException(RegraNegocioException e) {
        String mensagemErro = e.getMessage();
        return  new ApiErrors(mensagemErro);
    }


}
