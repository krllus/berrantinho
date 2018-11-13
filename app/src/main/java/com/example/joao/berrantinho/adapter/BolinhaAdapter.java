package com.example.joao.berrantinho.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.dialog.SimpleDialog;
import com.example.joao.berrantinho.model.Bolinha;
import com.example.joao.berrantinho.model.TipoBolinha;
import com.example.joao.berrantinho.viewholder.BolinhaHugeViewHolder;
import com.example.joao.berrantinho.viewholder.BolinhaSmallViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joao Carlos on 11/29/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class BolinhaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_BOLINHA_PEQUENA = 0;
  private static final int TYPE_BOLINHA_GIGANTE = 1;
  private List<Bolinha> items;
  private int expandedCommentPosition = RecyclerView.NO_POSITION;
  private FragmentManager fragmentManager;

  public BolinhaAdapter(FragmentManager supportFragmentManager) {
    this.items = new ArrayList<>();
    this.fragmentManager = supportFragmentManager;
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    switch (viewType) {
      case TYPE_BOLINHA_GIGANTE:
        return createBolinhaGiganteViewHolder(viewGroup);
      case TYPE_BOLINHA_PEQUENA:
      default:
        return createBolinhaPequenaViewHolder(viewGroup);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    switch (getItemViewType(position)) {
      case TYPE_BOLINHA_GIGANTE:
        bindBolinhaGigante(getItem(position), (BolinhaHugeViewHolder) viewHolder);
        break;
      case TYPE_BOLINHA_PEQUENA:
        bindBolinhaPequena(getItem(position), (BolinhaSmallViewHolder) viewHolder);
        break;
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (position < getItemCount() && getItemCount() > 0) {
      Bolinha item = getItem(position);
      if (item == null) return TYPE_BOLINHA_PEQUENA;

      switch (item.getTipoBolinha()) {
        case GIGANTE:
          return TYPE_BOLINHA_GIGANTE;
        case PEQUENA:
          return TYPE_BOLINHA_PEQUENA;
      }
    }

    return TYPE_BOLINHA_PEQUENA;
  }

  private BolinhaHugeViewHolder createBolinhaGiganteViewHolder(ViewGroup parent) {
    View view = LayoutInflater.from(
        parent.getContext()).inflate(R.layout.row_bolinha_gigante, parent, false);
    return new BolinhaHugeViewHolder(view);
  }

  private BolinhaSmallViewHolder createBolinhaPequenaViewHolder(ViewGroup parent) {
    View view = LayoutInflater.from(
        parent.getContext()).inflate(R.layout.row_bolinha_pequena, parent, false);
    return new BolinhaSmallViewHolder(view);
  }

  private void bindBolinhaGigante(final Bolinha bolinha, final BolinhaHugeViewHolder holder) {
    holder.nome.setText(bolinha.getNome());
    holder.volumeEmbalagem.setText(bolinha.getVolumeEmbalagem());
    holder.volumeBatida.setText(bolinha.getVolumeBatida());
    holder.totalEmbalagem.setText(bolinha.getTotalEmbalagem());
    holder.ingredientesList.setText(bolinha.getIngredientes());
    holder.ingredientes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final int position = holder.getAdapterPosition();
        if (position == RecyclerView.NO_POSITION) return;

        int index = items.indexOf(bolinha);

        //TransitionManager.beginDelayedTransition(commentsList, expandCollapse);
        //commentAnimator.setAnimateMoves(false);

        // collapse any currently expanded items
        if (RecyclerView.NO_POSITION != expandedCommentPosition) {
          items.get(index).setTipoBolinha(TipoBolinha.PEQUENA);
          notifyItemChanged(expandedCommentPosition);
        }

        // expand this item (if it wasn't already)
        if (expandedCommentPosition != position) {
          expandedCommentPosition = position;
          items.get(index).setTipoBolinha(TipoBolinha.GIGANTE);
          notifyItemChanged(position);
        } else {
          expandedCommentPosition = RecyclerView.NO_POSITION;
        }
      }
    });

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openDialog();
      }
    });
  }

  private void bindBolinhaPequena(final Bolinha bolinha, final BolinhaSmallViewHolder holder) {
    holder.nome.setText(bolinha.getNome());
    holder.volumeEmbalagem.setText(bolinha.getVolumeEmbalagem());
    holder.volumeBatida.setText(bolinha.getVolumeBatida());
    holder.totalEmbalagem.setText(bolinha.getTotalEmbalagem());

    holder.ingredientes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final int position = holder.getAdapterPosition();
        if (position == RecyclerView.NO_POSITION) return;

        int index = items.indexOf(bolinha);
        //TransitionManager.beginDelayedTransition(commentsList, expandCollapse);
        //commentAnimator.setAnimateMoves(false);

        // collapse any currently expanded items
        if (RecyclerView.NO_POSITION != expandedCommentPosition) {
          items.get(index).setTipoBolinha(TipoBolinha.PEQUENA);
          notifyItemChanged(expandedCommentPosition);
        }

        // expand this item (if it wasn't already)
        if (expandedCommentPosition != position) {
          expandedCommentPosition = position;
          items.get(index).setTipoBolinha(TipoBolinha.GIGANTE);
          notifyItemChanged(position);
        } else {
          expandedCommentPosition = RecyclerView.NO_POSITION;
        }
      }
    });

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openDialog();
      }
    });
  }

  private void openDialog() {

    SimpleDialog dialog = new SimpleDialog();
    dialog.show(fragmentManager, SimpleDialog.DIALOG_TAG);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void updateElements(List<Bolinha> elementos) {
    this.items.clear();
    this.items.addAll(elementos);
    notifyDataSetChanged();
  }

  private Bolinha getItem(int position) {
    return items.get(position);
  }
}

