package app.cookie.app.view;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cookie.app.R;

import java.util.List;

import app.cookie.app.model.Ingredient;

class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private final List<Ingredient> ingredients;

    public IngredientAdapter(List<Ingredient> ingredients) {
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

        holder.ingredientTextView.setText(ingredient.getQuantity() + " " + ingredient.getMeasure() + " " + ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientTextView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ingredientTextView = itemView.findViewById(R.id.ingredient_text_view);
        }
    }
}
