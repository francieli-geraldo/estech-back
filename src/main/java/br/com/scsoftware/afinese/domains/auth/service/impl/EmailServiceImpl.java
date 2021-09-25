package br.com.scsoftware.afinese.domains.auth.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.EmailService;
import br.com.scsoftware.afinese.domains.auth.service.Mail;
import freemarker.template.Configuration;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @author samuel-cruz
 */
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final Configuration fmConfiguration;

    @Override
    public void sendEmail(final Mail email, final String templateName) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            email.getModel().put("subject", email.getSubject());

            String from = "SC Software <suporte@scsoftware.com.br>";
            if (email.getFrom() != null)
                from = email.getFrom();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(email.getTo());
            email.setContent(geContentFromTemplate(email.getModel(), templateName));
            mimeMessageHelper.setText(email.getContent(), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String geContentFromTemplate(final Map<String, Object> model, final String templateName) {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(templateName.concat(".html")), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
