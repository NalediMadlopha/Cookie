package com.cookie.app.view.recipedetails.ingredientslist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cookie.app.R;
import com.cookie.app.model.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.IngredientViewHolder> {

    private final Context context;
    private final List<Ingredient> ingredients;

    IngredientsListAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);

        String ingredientText = context.getResources().getString(R.string.add_widget_item_format, ingredient.getQuantity(), ingredient.getMeasure(), ingredient.getIngredient());
        holder.ingredientTextView.setText(ingredientText);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ingredient_item_text_view) TextView ingredientTextView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
