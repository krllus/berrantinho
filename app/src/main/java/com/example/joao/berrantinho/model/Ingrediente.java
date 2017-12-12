package com.example.joao.berrantinho.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.example.joao.berrantinho.BR;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Joao Carlos on 12/12/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class Ingrediente implements Observable {

    private PropertyChangeRegistry pcr = new PropertyChangeRegistry();

    private String nome;
    private Double quantidade;

    public Ingrediente() {
    }

    @Bindable
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        pcr.notifyChange(this, BR.nome);
    }

    @Bindable
    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
        pcr.notifyChange(this, BR.quantidade);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        pcr.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        pcr.remove(onPropertyChangedCallback);
    }

    public static List<Ingrediente> createDumbElements(int quantidade) {
        List<Ingrediente> lista = new ArrayList<>();
        Ingrediente ingrediente;

        for (int i = 0; i < quantidade; i++) {
            ingrediente = new Ingrediente();
            ingrediente.setNome(String.format(Locale.getDefault(), "Nome %02d", i));
            Double value = Math.random() * 5 + 1;
            ingrediente.setQuantidade(value);
            lista.add(ingrediente);
        }

        return lista;
    }
}
