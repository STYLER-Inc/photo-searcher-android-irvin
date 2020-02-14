package view.detail;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import javax.inject.Inject;

import butterknife.BindView;
import data.factories.ViewModelFactory;
import injection.BaseFragment;
import jp.styler.challenge.R;
import model.Photo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailFragment extends BaseFragment {

    public static final String TAG = "ImageDetailFragment";
    public static final String EXTRA_PHOTO = "ImageDetailFragment.Photo";

    @BindView(R.id.fragment_image_detail_photo)
    PhotoView photoView;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_image_detail;
    }

    @Inject
    ViewModelFactory viewModelFactory;

    private ImageDetailViewModel viewModel;

    public static ImageDetailFragment newInstance(Photo photo) {
        ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PHOTO, photo);
        imageDetailFragment.setArguments(bundle);
        return imageDetailFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(ImageDetailViewModel.class);

        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey(EXTRA_PHOTO)) {
            Photo photo = bundle.getParcelable(EXTRA_PHOTO);

            Glide.with(getContext()).load(photo.getHighRes()).into(photoView);
        }
    }
}
