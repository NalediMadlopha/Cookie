package app.cookie.app._new_architecture.view.stepdetails;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cookie.app.R;

import app.cookie.app._new_architecture.model.Recipe;
import app.cookie.app._new_architecture.model.Step;
import app.cookie.app.viewmodel.StepDetailsViewModel;

public class StepDetailsFragment extends Fragment implements StepDetailsFragmentView {

    private final int FIRST_STEP_ID = 0;
    private final int STEP_ID_INCREMENT = 1;
    private TextView stepShortDescriptionTextView;
    private TextView stepDescriptionTextView;
    private Button previousStepButton;
    private Button nextStepButton;
    private View viewSpace;
    private StepDetailsViewModel viewModel;
    private Recipe recipe;
    private Step step;
    private ProgressBar progressBar;
    private LinearLayout mainlayout;

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        progressBar = view.findViewById(R.id.step_details_progress_bar);
        mainlayout = view.findViewById(R.id.step_details_main_layout);
        stepShortDescriptionTextView = view.findViewById(R.id.step_short_description);
        stepDescriptionTextView = view.findViewById(R.id.step_description);
        previousStepButton = view.findViewById(R.id.previous_step_button);
        nextStepButton = view.findViewById(R.id.next_step_button);
        viewSpace = view.findViewById(R.id.view_space);

        previousStepButton.setOnClickListener(PreviousStepButtonClickListener);
        nextStepButton.setOnClickListener(NextStepButtonClickListener);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(StepDetailsViewModel.class);

        viewModel.getRecipe().observe(this, recipe -> {
            this.recipe = recipe;

            updateUi();
            setupButtons();
        });
    }

    private void updateUi() {
        step = recipe.getSteps().get(viewModel.getStepId());
        stepShortDescriptionTextView.setText(step.getShortDescription());
        stepDescriptionTextView.setText(step.getDescription());
        progressBar.setVisibility(View.GONE);
        mainlayout.setVisibility(View.VISIBLE);
    }

    private void setupButtons() {
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if (!isTablet) {
            if (viewModel.getStepId() != FIRST_STEP_ID) {
                displayPreviousStepButton();
            } else {
                hidePreviousStepButton();
            }

            if ((viewModel.getStepId() + STEP_ID_INCREMENT) != recipe.getSteps().size()) {
                displayNextStepButton();
            } else {
                hideNextStepButton();
            }

            if (nextStepButton.getVisibility() == View.VISIBLE && previousStepButton.getVisibility() == View.VISIBLE) {
                viewSpace.setVisibility(View.VISIBLE);
            } else {
                viewSpace.setVisibility(View.GONE);
            }
        }
    }

    View.OnClickListener PreviousStepButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.setStepId(step.getId() - 1);
            updateStepDetails();
        }
    };

    View.OnClickListener NextStepButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.setStepId(step.getId() + 1);
            updateStepDetails();
        }
    };

//    @Override
//    public void showProgress() {
//        progressBar.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hideProgress() {
//        progressBar.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void displayStepDetails(Step step) {
//        stepShortDescriptionTextView.setText(step.getShortDescription());
//        stepDescriptionTextView.setText(step.getDescription());
//    }

    @Override
    public void displayPreviousStepButton() {
        previousStepButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePreviousStepButton() {
        previousStepButton.setVisibility(View.GONE);
    }

    @Override
    public void displayNextStepButton() {
        nextStepButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNextStepButton() {
        nextStepButton.setVisibility(View.GONE);
    }

    public void updateStepDetails() {
        StepDetailsFragment fragment = new StepDetailsFragment();

        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_details_container, fragment)
                .commit();
    }

//    private class MyMediaSession extends MediaSessionCompat.Callback {
//
//        @Override
//        public void onPlay() {
//            super.onPlay();
//        }
//
//        @Override
//        public void onPause() {
//            super.onPause();
//        }
//
//        @Override
//        public void onSkipToPrevious() {
//            super.onSkipToPrevious();
//        }
//
//        @Override
//        public void onSkipToNext() {
//            super.onSkipToNext();
//        }
//    }
}