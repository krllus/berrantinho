package com.example.joao.berrantinho.features.suplementacao;

import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.joao.berrantinho.BaseFragment;
import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.adapter.SimpleAdapter;
import com.example.joao.berrantinho.model.Simple;
import com.example.joao.berrantinho.model.SimpleEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Joao Carlos on 11/24/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class SuplementacaoFragment extends BaseFragment implements View.OnClickListener {

  RecyclerView recyclerView;
  SimpleAdapter adapter;

  SimpleEnum simpleEnum;
  TextView filterVazio;
  TextView filterRepor;
  TextView filterComProduto;
  TextView filterTodos;

  CardView filterCardContainer;

  public static SuplementacaoFragment newInstance() {
    return new SuplementacaoFragment();
  }

  @Override
  public int getFragmentLayout() {
    return R.layout.fragment_suplementacao;
  }

  @Override
  public void setUpCustomViews(View rootView) {
    filterCardContainer = rootView.findViewById(R.id.card_filter_container);

    recyclerView = rootView.findViewById(R.id.recycler_view);

    filterVazio = rootView.findViewById(R.id.filter_vazio);
    filterRepor = rootView.findViewById(R.id.filter_repor);
    filterComProduto = rootView.findViewById(R.id.filter_com_produto);
    filterTodos = rootView.findViewById(R.id.filter_all);
  }

  @Override
  public void setUpLayoutListeners() {
    adapter = new SimpleAdapter();
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(layoutManager);

    filterVazio.setOnClickListener(this);
    filterRepor.setOnClickListener(this);
    filterComProduto.setOnClickListener(this);
    filterTodos.setOnClickListener(this);

    updateUI();
  }

  @Override
  public void initDependencies() {
  }

  @Override
  public void updateUI() {
    adapter.updateElements(generateDumbElements());
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.filter_vazio:
        simpleEnum = SimpleEnum.A;
        applyFilter();
        break;

      case R.id.filter_repor:
        simpleEnum = SimpleEnum.B;
        applyFilter();
        break;

      case R.id.filter_com_produto:
        simpleEnum = SimpleEnum.C;
        applyFilter();
        break;

      case R.id.filter_all:
        simpleEnum = SimpleEnum.D;
        applyFilter();
        break;
    }
  }

  public List<Simple> generateDumbElements() {
    List<Simple> list = new ArrayList<>();
    Simple simple;

    String content = getResources().getString(R.string.dumb_large_text_I);

    for (int i = 1; i < 10; i++) {
      String title = String.format(Locale.getDefault(), "Title %02d", i);
      simple = new Simple(title, content);
      list.add(simple);
    }
    return list;
  }

  private void applyFilter() {
    if (simpleEnum == SimpleEnum.A) {
      filterVazio.setTypeface(null, Typeface.BOLD_ITALIC);
      filterVazio.setSelected(true);
    } else {
      filterVazio.setTypeface(null, Typeface.NORMAL);
      filterVazio.setSelected(false);
    }

    if (simpleEnum == SimpleEnum.B) {
      filterRepor.setTypeface(null, Typeface.BOLD_ITALIC);
      filterRepor.setSelected(true);
    } else {
      filterRepor.setTypeface(null, Typeface.NORMAL);
      filterRepor.setSelected(false);
    }

    if (simpleEnum == SimpleEnum.C) {
      filterComProduto.setTypeface(null, Typeface.BOLD_ITALIC);
      filterComProduto.setSelected(true);
    } else {
      filterComProduto.setTypeface(null, Typeface.NORMAL);
      filterComProduto.setSelected(false);
    }

    if (simpleEnum == SimpleEnum.D) {
      filterTodos.setTypeface(null, Typeface.BOLD_ITALIC);
      filterTodos.setSelected(true);
    } else {
      filterTodos.setTypeface(null, Typeface.NORMAL);
      filterTodos.setSelected(false);
    }
  }
}
