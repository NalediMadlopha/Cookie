package app.cookie.app._new_architecture.view.recipedetails.stepslist;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cookie.app.R;

import app.cookie.app._new_architecture.viewmodel.RecipeDetailsViewModel;

public class StepsListFragment extends Fragment {

    private RecipeDetailsViewModel viewModel;
    private RecyclerView stepsRecyclerView;

    public StepsListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        stepsRecyclerView = rootView.findViewById(R.id.steps_recycler_view);
        stepsRecyclerView.setLayoutManager(layoutManager);
        stepsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(RecipeDetailsViewModel.class);
        viewModel.getRecipe().observe(this, recipe -> {
            stepsRecyclerView.setAdapter(new StepsListAdapter(recipe.getId(), recipe.getName(), recipe.getSteps()));
        });
    }
}
