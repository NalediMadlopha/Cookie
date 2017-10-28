package app.cookie.app.view.ingredients;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cookie.app.R;

import java.util.List;

import app.cookie.app.model.Ingredient;
import app.cookie.app.viewmodel.IngredientsFragmentViewModel;

public class IngredientsFragment extends Fragment implements IngredientFragmentView {

    private RecyclerView ingredientsRecyclerView;
    private IngredientsFragmentViewModel viewModel;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new IngredientsFragmentViewModel(this, getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ingredientsRecyclerView = rootView.findViewById(R.id.ingredients_recycler_view);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void displayIngredients(List<Ingredient> ingredients) {
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsRecyclerView.setAdapter(new IngredientAdapter(ingredients));
    }
}
