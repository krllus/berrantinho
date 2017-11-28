package com.example.joao.berrantinho.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Joao Carlos on 11/27/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class FilterHideBehavior extends CoordinatorLayout.Behavior<View> {

    public FilterHideBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());

        child.setTranslationY(translationY);

        //TODO implement this method
        // scroll
        // enter always
        // snap

        return true;
    }
}
