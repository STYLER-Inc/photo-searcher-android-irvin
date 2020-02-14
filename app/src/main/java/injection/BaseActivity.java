package injection;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
    }
}
