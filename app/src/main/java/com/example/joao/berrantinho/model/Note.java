package com.example.joao.berrantinho.model;

import java.io.File;

/**
 * Created by João Carlos on 5/11/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class Note {
  private final String name;
  private final File file;
  private boolean selected;

  public Note(String name, File file) {
    this.name = name;
    this.file = file;
  }

  public String getName() {
    return name;
  }

  public File getFile() {
    return file;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
}
