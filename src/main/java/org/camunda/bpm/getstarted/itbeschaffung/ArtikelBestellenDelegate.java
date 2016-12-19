package org.camunda.bpm.getstarted.itbeschaffung;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ArtikelBestellenDelegate implements JavaDelegate {

  // TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  private final static Logger LOGGER = Logger.getLogger(ArtikelBestellenDelegate.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
      String var = (String) execution.getVariable("bezeichnung");      
      String recipient = "happyhwx888@googlemail.com";
      String etext = "Sehr geehrte Damen/Herren, \n\n Ich w\u00fcrde gerne den Artikel bestellen: " + var + ".\n\n Mit freundlichen Gr\u00fc\u00dfen, \n\n Demo Demo";
      
      
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("demodemo1234512345@gmail.com");
          email.setSubject("Artikel Bestellen");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
          LOGGER.info("Task Assignment Email successfully sent to address: " + recipient); 

        } catch (Exception e) {
          LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
        }
      
    }

}
