package br.com.anderson.southsystem.desafiobackvotos.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.anderson.southsystem.desafiobackvotos.dto.ErroBusinessDTO;
import br.com.anderson.southsystem.desafiobackvotos.dto.ErroValidacaoCamposDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;

@RestControllerAdvice
public class ErrosHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacaoCamposDTO> handle(MethodArgumentNotValidException exception) {
		return getErrosValidacao(exception);
	}

	private List<ErroValidacaoCamposDTO> getErrosValidacao(MethodArgumentNotValidException exception) {
		List<FieldError> camposComErro = exception.getBindingResult().getFieldErrors();
		
	    return camposComErro.stream().map( e -> {
	    	ErroValidacaoCamposDTO erro = new ErroValidacaoCamposDTO(
	    			e.getField(), e.getDefaultMessage());
	    	return erro;
	    })
	    .collect(Collectors.toList());
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DesafioBackVotosException.class)
	public ErroBusinessDTO desafioBackVotosException(DesafioBackVotosException exception) {
		return new ErroBusinessDTO(exception.getMessage());
	}

}