package br.com.scsoftware.afinese.domains.auth.service;

/**
 * @author samuel-cruz
 */
public interface EmailService {

    void sendEmail(Mail email, String templateName);
}
