package view.grid;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import data.factories.ViewModelFactory;
import injection.BaseFragment;
import jp.styler.challenge.R;
import view.detail.ImageDetailFragment;
import view.grid.adapter.GridAdapter;
import view.grid.adapter.ImageClickedListener;


public class ImageGridFragment extends BaseFragment {

    public static final String TAG = "ImageGridFragment";


    @BindView(R.id.fragment_grid_view)
    GridView gridView;
    @BindView(R.id.fragment_grid_input_searh)
    EditText inputSearch;
    @BindView(R.id.fragment_grid_loading)
    ProgressBar loading;
    @BindView(R.id.fragment_grid_error)
    TextView errorView;


    @Inject
    ViewModelFactory viewModelFactory;

    private GridViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_image_grid;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this, viewModelFactory).get(GridViewModel.class);

        observeViewModel();
    }

    private ImageClickedListener imageClickedListener =
            clickedPhoto -> getBaseActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_activity_content, ImageDetailFragment.newInstance(clickedPhoto))
                    .addToBackStack(null).commit();

    private void observeViewModel() {
        viewModel.getPhotoUrls().observe(getViewLifecycleOwner(), photos -> {
            if (photos.size() > 0) {
                gridView.setVisibility(View.VISIBLE);
                GridAdapter gridAdapter = new GridAdapter(getContext(), photos, imageClickedListener);
                gridView.setAdapter(gridAdapter);
                errorView.setText("No Results Found!");
            } else {
                errorView.setVisibility(View.VISIBLE);
            }

        });

        viewModel.getError().observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) if (isError) {
                errorView.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
                errorView.setText("An Error Occurred While Loading Data!");
            } else {
                errorView.setVisibility(View.GONE);
                errorView.setText(null);
            }
        });

        viewModel.getLoading().observe(getViewLifecycleOwner(), isloading -> {
            if (isloading != null) {
                loading.setVisibility(isloading ? View.VISIBLE : View.GONE);
                if (isloading) {
                    errorView.setVisibility(View.GONE);
                    gridView.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick(R.id.fragment_grid_search_btn)
    public void performSearch() {

        String typedText = inputSearch.getText().toString();

        if (!TextUtils.isEmpty(typedText)) {
            viewModel.searchPhotos(typedText);
        } else {
            Toast.makeText(getContext(), R.string.enter_search, Toast.LENGTH_SHORT).show();
        }
    }
}
