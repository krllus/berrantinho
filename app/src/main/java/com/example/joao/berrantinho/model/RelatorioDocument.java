package com.example.joao.berrantinho.model;

import com.example.joao.berrantinho.utils.BioxDocument;

/**
 * Created by João Carlos on 5/14/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class RelatorioDocument extends BioxDocument {

  private String message;

  public RelatorioDocument() {
    super(new DocumentTypeRelatorio(), new DocumentDateToday(), new DocumentFileExtensionPDF());
  }

  @Override
  public String getDocumentSubFolder() {
    return "Relatorios";
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
