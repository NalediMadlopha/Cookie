package app.cookie.app.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cookie.app.R;

import java.util.List;

import app.cookie.app.model.Recipe;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeCardViewHolder> {

    private final List<Recipe> recipeList;

    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    class RecipeCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView recipeImageView;
        TextView recipeNameTextView;

        RecipeCardViewHolder(View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipe_name_text_view);
            recipeImageView = itemView.findViewById(R.id.recipe_image_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public RecipeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);

        return new RecipeCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeCardViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        holder.recipeNameTextView.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
