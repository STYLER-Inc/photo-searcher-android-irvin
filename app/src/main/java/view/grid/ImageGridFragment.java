package view.grid;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import data.factories.ViewModelFactory;
import injection.BaseFragment;
import jp.styler.challenge.R;
import view.grid.adapter.GridAdapter;


public class ImageGridFragment extends BaseFragment {

    public static final String TAG = "ImageGridFragment";


    @BindView(R.id.fragment_grid_view)
    GridView gridView;
    @BindView(R.id.fragment_grid_input_searh)
    EditText inputSearch;


    @Inject
    ViewModelFactory viewModelFactory;

    private GridViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_image_grid;
    }

    public static ImageGridFragment newInstance() {
        return new ImageGridFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GridViewModel.class);

        viewModel.getPhotoUrls().observe(this, photoUrls -> {

            GridAdapter gridAdapter = new GridAdapter(getContext(), photoUrls);

            gridView.setAdapter(gridAdapter);

        });
    }

    @OnClick(R.id.fragment_grid_search_btn)
    public void performSearch() {

        String typedText = inputSearch.getText().toString();

        if (TextUtils.isEmpty(typedText)) {
            viewModel.searchPhotos(typedText);
        } else {
            Toast.makeText(getContext(), R.string.enter_search, Toast.LENGTH_SHORT).show();
        }
    }
}
