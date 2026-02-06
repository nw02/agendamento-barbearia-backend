package org.example.sistemaagendamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail handleNotFound(RecursoNaoEncontradoException e){
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pb.setTitle("Recurso não encontrado");
        return pb;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleRequest(IllegalArgumentException e){
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        pb.setTitle("Erro na validação de dados");
        return pb;
    }

    @ExceptionHandler(ConflitoDeHorarioException.class)
    public ProblemDetail handleConflito(ConflitoDeHorarioException e){
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        pb.setTitle("Conflito de agendamento");
        return pb;
    }
}
