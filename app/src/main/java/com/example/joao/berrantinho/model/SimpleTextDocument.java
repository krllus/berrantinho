package com.example.joao.berrantinho.model;

import com.example.joao.berrantinho.utils.BioxDocument;

/**
 * Created by João Carlos on 5/14/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class SimpleTextDocument extends BioxDocument {

    public SimpleTextDocument() {
        super(new DocumentTypeText(), new DocumentDateToday(), new DocumentFileExtensionTXT());
    }

    @Override
    public String getDocumentSubFolder() {
        return "Text";
    }

    public String getMessage() {
        return "Simple Text";
    }
}
