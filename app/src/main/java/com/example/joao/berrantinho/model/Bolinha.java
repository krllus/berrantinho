package com.example.joao.berrantinho.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joao Carlos on 11/29/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class Bolinha {

  private String nome;
  private String volumeBatida;
  private String totalEmbalagem;
  private String volumeEmbalagem;
  private String ingredientes;

  private TipoBolinha tipoBolinha;

  public Bolinha() {
  }

  public static List<Bolinha> createDummyBolinhaItens() {
    List<Bolinha> bolinhas = new ArrayList<>();
    Bolinha bolinha;

    for (int i = 0; i < 10; i++) {
      bolinha = new Bolinha();
      //setdefaultbolinhaparameters
      bolinha.setNome("Produtão " + i);
      bolinha.setTotalEmbalagem("40");
      bolinha.setVolumeEmbalagem("25kg");
      bolinha.setVolumeBatida("300kg");
      bolinha.setIngredientes("" +
          "Produto01 - 10kg\n" +
          "Produto02 - 20kg\n" +
          "Produto03 - 40kg\n" +
          "Produto04 - 60kg\n" +
          "Produto05 - 30kg\n" +
          "Produto06 - 20kg");
      bolinha.setTipoBolinha(TipoBolinha.PEQUENA);
      bolinhas.add(bolinha);
    }
    return bolinhas;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getVolumeBatida() {
    return volumeBatida;
  }

  public void setVolumeBatida(String volumeBatida) {
    this.volumeBatida = volumeBatida;
  }

  public String getTotalEmbalagem() {
    return totalEmbalagem;
  }

  public void setTotalEmbalagem(String totalEmbalagem) {
    this.totalEmbalagem = totalEmbalagem;
  }

  public String getVolumeEmbalagem() {
    return volumeEmbalagem;
  }

  public void setVolumeEmbalagem(String volumeEmbalagem) {
    this.volumeEmbalagem = volumeEmbalagem;
  }

  public String getIngredientes() {
    return ingredientes;
  }

  public void setIngredientes(String ingredientes) {
    this.ingredientes = ingredientes;
  }

  public TipoBolinha getTipoBolinha() {
    return tipoBolinha;
  }

  public void setTipoBolinha(TipoBolinha tipoBolinha) {
    this.tipoBolinha = tipoBolinha;
  }
}
