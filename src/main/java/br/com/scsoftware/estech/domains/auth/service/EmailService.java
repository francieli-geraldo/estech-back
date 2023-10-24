package br.com.scsoftware.estech.domains.auth.service;

/**
 * @author samuel-cruz
 */
public interface EmailService {

    void sendEmail(Mail email, String templateName);
}
