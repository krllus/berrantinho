package com.example.joao.berrantinho.utils;

import com.example.joao.berrantinho.model.DocumentDate;
import com.example.joao.berrantinho.model.DocumentFileExtension;
import com.example.joao.berrantinho.model.DocumentType;

/**
 * Created by João Carlos on 5/14/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 * https://en.wikipedia.org/wiki/Composition_over_inheritance
 */
public abstract class BioxDocument {
  private final DocumentType docType;
  private final DocumentDate docDate;
  private final DocumentFileExtension docFileExtension;

  public BioxDocument(DocumentType documentType,
      DocumentDate documentDate,
      DocumentFileExtension documentFileExtension) {
    this.docType = documentType;
    this.docDate = documentDate;
    this.docFileExtension = documentFileExtension;
  }

  public String getFileNameFormatterString() {
    return docType.getType()
        + "_"
        + docDate.getDate()
        + "_%02d."
        + docFileExtension.getFileExtension();
  }

  public abstract String getDocumentSubFolder();
}
