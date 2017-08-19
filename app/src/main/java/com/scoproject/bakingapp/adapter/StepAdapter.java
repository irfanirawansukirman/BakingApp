package com.scoproject.bakingapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scoproject.bakingapp.R;
import com.scoproject.bakingapp.data.Ingredient;
import com.scoproject.bakingapp.data.Step;

import java.util.List;

/**
 * Created by ibnumuzzakkir on 8/17/17.
 * Android Engineer
 * SCO Project
 */

public class StepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Step> mStepList;
    private List<Ingredient> mIngredientList;
    private LayoutInflater mLayoutInflater;

    public StepAdapter(List<Step> stepList, List<Ingredient> ingredientList){
        mStepList = stepList;
        mIngredientList = ingredientList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = null;
        switch (viewType){
            case R.layout.item_ingredient:
                itemView = mLayoutInflater.inflate(R.layout.item_ingredient, parent, false);
                return new IngredientViewHolder(itemView);
            case R.layout.item_step:
                itemView = mLayoutInflater.inflate(R.layout.item_step, parent, false);
                return new StepViewHolder(itemView);
            default:
                itemView = mLayoutInflater.inflate(R.layout.item_ingredient, parent, false);
                return new IngredientViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case R.layout.item_ingredient:
                Ingredient ingredient = mIngredientList.get(position);
                IngredientViewHolder ingredientViewHolder = (IngredientViewHolder) holder;
                ingredientViewHolder.mIngredientName.setText(ingredient.getIngredient());
                break;
            case R.layout.item_step:
                Step step = mStepList.get(position - mIngredientList.size());
                StepViewHolder stepViewHolder = (StepViewHolder) holder;
                stepViewHolder.mStepName.setText(step.getShortDescription());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position < mIngredientList.size())
            return R.layout.item_ingredient;
        else
            return R.layout.item_step;
    }
    @Override
    public int getItemCount() {
        return mIngredientList.size() + mStepList.size();
    }


    public class StepViewHolder extends RecyclerView.ViewHolder {
        TextView mStepName;
        public StepViewHolder(View itemView) {
            super(itemView);
            mStepName = itemView.findViewById(R.id.tv_step_name);

        }
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView mIngredientName;
        public IngredientViewHolder(View itemView) {
            super(itemView);
            mIngredientName = itemView.findViewById(R.id.tv_ingredient_name);

        }
    }
}
