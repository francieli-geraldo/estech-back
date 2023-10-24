package br.com.scsoftware.estech.domains.auth.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author samuel-cruz
 */
@Builder
@Getter
@Setter
public class Mail {
    private final String to;
    private final String from;
    private final String subject;
    private String content;
    private final Map<String, Object> model;
}