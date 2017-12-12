package com.example.joao.berrantinho.adapter;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.model.Ingrediente;

import java.util.List;

/**
 * Created by Joao Carlos on 12/12/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class IngredientesAdapter extends SingleLayoutAdapter<Ingrediente> {

    public IngredientesAdapter() {
        super(R.layout.row_ingrediente);
    }

    @Override
    protected Ingrediente getObjForPosition(int position) {
        return elements.get(position);
    }

    public List<Ingrediente> getIngredientes() {
        return this.elements;
    }
}
