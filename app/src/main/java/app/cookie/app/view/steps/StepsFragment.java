package app.cookie.app.view.steps;


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

import java.util.List;

import app.cookie.app.model.Step;
import app.cookie.app.viewmodel.StepsFragmentViewModel;

public class StepsFragment extends Fragment implements StepsFragmentView {

    private RecyclerView stepsRecyclerView;
    private StepsFragmentViewModel viewModel;

    public StepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new StepsFragmentViewModel(this, getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        stepsRecyclerView = rootView.findViewById(R.id.steps_recycler_view);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void displaySteps(int recipeId, String recipeName, List<Step> steps) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        stepsRecyclerView.setLayoutManager(layoutManager);
        stepsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
        stepsRecyclerView.setAdapter(new StepsAdapter(recipeId, recipeName, steps));
    }
}
