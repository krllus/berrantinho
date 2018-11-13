package com.example.joao.berrantinho.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.joao.berrantinho.R;

/**
 * Created by Joao Carlos on 11/29/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class BolinhaHugeViewHolder extends RecyclerView.ViewHolder {

  public TextView nome;
  public TextView volumeBatida;
  public TextView totalEmbalagem;
  public TextView volumeEmbalagem;
  public TextView ingredientes;
  public TextView ingredientesList;

  public BolinhaHugeViewHolder(View itemView) {
    super(itemView);
    nome = itemView.findViewById(R.id.row_fabricasuplementos_product_name_content);
    volumeBatida = itemView.findViewById(R.id.row_fabricasuplementos_product_batida_volume_content);
    totalEmbalagem =
        itemView.findViewById(R.id.row_fabricasuplementos_product_batida_total_embalagens_content);
    volumeEmbalagem = itemView.findViewById(R.id.row_fabricasuplementos_product_volume_content);
    ingredientes = itemView.findViewById(R.id.ingredientes);
    ingredientesList = itemView.findViewById(R.id.ingredientes_list);
  }
}