package view.detail;


import androidx.fragment.app.Fragment;

import injection.BaseFragment;
import jp.styler.challenge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailFragment extends BaseFragment {

    public static final String TAG = "ImageDetailFragment";

    @Override
    protected int layoutRes() {
        return R.layout.fragment_image_detail;
    }

    public static ImageDetailFragment newInstance() {
        return new ImageDetailFragment();
    }
}
