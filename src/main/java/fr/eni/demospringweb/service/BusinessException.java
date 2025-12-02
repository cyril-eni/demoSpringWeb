package fr.eni.demospringweb.service;

/**
 * Classe d'exception eprsonalisé utilisée pour nos validations Métier
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}