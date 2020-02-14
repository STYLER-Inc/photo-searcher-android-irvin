package view.main;

import android.os.Bundle;

import injection.BaseActivity;
import jp.styler.challenge.R;
import view.grid.ImageGridFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_content, new ImageGridFragment()).commit();
    }
}
