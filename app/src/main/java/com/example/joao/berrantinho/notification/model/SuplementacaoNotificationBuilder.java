package com.example.joao.berrantinho.notification.model;

/**
 * Created by Joao Carlos on 11/20/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class SuplementacaoNotificationBuilder {
  private String messageTitle;
  private String messageContent;

  public SuplementacaoNotificationBuilder setMessageTitle(String messageTitle) {
    this.messageTitle = messageTitle;
    return this;
  }

  public SuplementacaoNotificationBuilder setMessageContent(String messageContent) {
    this.messageContent = messageContent;
    return this;
  }

  public SuplementacaoNotification createSuplementacaoNotification() {
    return new SuplementacaoNotification(messageTitle, messageContent);
  }
}
