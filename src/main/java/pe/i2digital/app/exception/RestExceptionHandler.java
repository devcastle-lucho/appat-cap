/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pe.i2digital.app.exception.domain.ApiError;
import pe.i2digital.app.exception.domain.AuthAppException;

//Error Handling 
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, 
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" - El media type no es compatible. Los media type admitidos son:");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        log.error("ERROR HTTP :", ex);
        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,builder.substring(0, builder.length()-2), ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, 
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR HTTP :", ex);
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED);
        StringBuilder builder = new StringBuilder();
        ex.getSupportedHttpMethods().forEach(h->builder.append(h.name()).append(", "));
        apiError.setMessage(String.format("No se encuentra el método '%s' y soporta solamente '%s'", ex.getMethod(),
                builder.substring(0, builder.length()-2)));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR HTTP :", ex);
        String message="Falta el parámetro: '"+ex.getParameterName()+ "' ";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, message, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       log.error("ERROR HTTP :", ex);
        String message="Falta la parte de petición: '"+ex.getRequestPartName()+ "' ";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, message, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
         log.error("ERROR HTTP :", ex);
         ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
         apiError.setMessage("Error de validación");
         apiError.addValidationErrors(ex.getFieldErrors());
         apiError.addValidationError(ex.getGlobalErrors());
         return buildResponseEntity(apiError);
    }
   
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,  WebRequest request){
        //log.error("ERROR HTTP :", ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        String message=String.format("El parametro '%s' de valor '%s' no se pudo convertir a tipo '%s'", 
                ex.getName(),ex.getValue(),ex.getRequiredType().getSimpleName());
        apiError.setMessage(message);
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }     
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("ERROR SERVIDOR :", ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage("¡Hubo inconvenientes con el servidor!");
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        log.error("ERROR SERVIDOR :", ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage("Excepcion en ejecución");
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    //org.springframework.security.access.AccessDeniedException
    //Manejo de excepcion de acceso denegado - 403
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException ex,WebRequest request){
        ApiError apiError= new ApiError(HttpStatus.FORBIDDEN);
        apiError.setMessage("Acceso denegado");
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AuthAppException.class)
    protected ResponseEntity<Object> handleAuthAppException(Exception ex) {
        //log.error("ERROR :", ex);
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
        apiError.setMessage("No autenticado");
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
