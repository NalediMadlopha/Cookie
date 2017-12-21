package app.cookie.app.view.stepdetails;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import app.cookie.app.dependency.App;
import app.cookie.app.model.Recipe;
import app.cookie.app.model.Step;
import app.cookie.app.viewmodel.StepDetailsViewModel;
import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app.stringdef.CookieConstants.KEY.STEP_ID;

public class StepDetailsFragment extends Fragment implements StepDetailsFragmentView {

    private static final int FIRST_STEP_ID = 0;
    private static final int STEP_ID_INCREMENT = 1;
    private StepDetailsViewModel viewModel;
    private Recipe recipe;
    private Step step;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;
    private SimpleExoPlayer exoPlayer;

    @BindView(R.id.step_short_description) TextView stepShortDescriptionTextView;
    @BindView(R.id.step_description) TextView stepDescriptionTextView;
    @BindView(R.id.previous_step_button) Button previousStepButton;
    @BindView(R.id.next_step_button) Button nextStepButton;
    @BindView(R.id.view_space) View viewSpace;
    @BindView(R.id.step_details_progress_bar) ProgressBar progressBar;
    @BindView(R.id.step_details_main_layout) LinearLayout mainlayout;
    @BindView(R.id.exoPlayerView) SimpleExoPlayerView exoPlayerView;
    @BindBool(R.bool.isLandscape) boolean isLandspace;

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.appComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(StepDetailsViewModel.class);
        if (getArguments() != null) {
            viewModel.init(getArguments().getInt(RECIPE_ID), getArguments().getInt(STEP_ID));
        }
        viewModel.getRecipe().observe(this, recipe -> {
            this.recipe = recipe;
            updateUi();
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    public void displayPreviousStepButton() {
        previousStepButton.setVisibility(View.VISIBLE);
        previousStepButton.setOnClickListener(previousStepButtonClickListener);
    }

    @Override
    public void hidePreviousStepButton() {
        previousStepButton.setVisibility(View.GONE);
    }

    @Override
    public void displayNextStepButton() {
        nextStepButton.setVisibility(View.VISIBLE);
        nextStepButton.setOnClickListener(nextStepButtonClickListener);
    }

    @Override
    public void hideNextStepButton() {
        nextStepButton.setVisibility(View.GONE);
    }

    private void updateUi() {
        step = recipe.getSteps().get(viewModel.getStepId());
        if (!step.getVideoURL().isEmpty()) {
            initializePlayer(Uri.parse(step.getVideoURL()));
        } else {
            exoPlayerView.setVisibility(View.GONE);
        }

        if (!isLandspace) {
            stepShortDescriptionTextView.setText(step.getShortDescription());
            stepDescriptionTextView.setText(step.getDescription());
            progressBar.setVisibility(View.GONE);
            mainlayout.setVisibility(View.VISIBLE);
            setupButtons();
        } else {
            hideSystemUi();
        }
    }

    private void setupButtons() {
        if (!getResources().getBoolean(R.bool.isTablet)) {
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

    View.OnClickListener previousStepButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.setStepId(step.getId() - 1);
            updateStepDetails();
        }
    };

    View.OnClickListener nextStepButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.setStepId(step.getId() + 1);
            updateStepDetails();
        }
    };

    public void updateStepDetails() {
        StepDetailsFragment fragment = new StepDetailsFragment();

        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_details_container, fragment)
                .commit();
    }

    private void initializePlayer(Uri uri) {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        exoPlayerView.setPlayer(exoPlayer);

        exoPlayer.setPlayWhenReady(playWhenReady);
        exoPlayer.seekTo(currentWindow, playbackPosition);

        MediaSource mediaSource = buildMediaSource(uri);
        exoPlayer.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory(getString(R.string.app_name)),
                new DefaultExtractorsFactory(), null, null);
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            playbackPosition = exoPlayer.getCurrentPosition();
            currentWindow = exoPlayer.getCurrentWindowIndex();
            playWhenReady = exoPlayer.getPlayWhenReady();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        exoPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}