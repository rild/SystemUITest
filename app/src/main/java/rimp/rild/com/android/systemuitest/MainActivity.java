package rimp.rild.com.android.systemuitest;

import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mHideNavigationButton;
    Button mLowProfileButton;
    Button mVisibleButton;

    ToggleButton mToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHideNavigationButton = (Button) findViewById(R.id.button_hide_navigation);
        mLowProfileButton = (Button) findViewById(R.id.button_low_profile);
        mVisibleButton = (Button) findViewById(R.id.button_visible);

        mHideNavigationButton.setOnClickListener(this);
        mLowProfileButton.setOnClickListener(this);
        mVisibleButton.setOnClickListener(this);

        mToggleButton = (ToggleButton) findViewById(R.id.button_fullscreen);
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Window window = getWindow();
                WindowManager.LayoutParams windowParams = window.getAttributes();

                if (isChecked) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }

                window.setAttributes(windowParams);
            }
        });


    }

    @Override
    public void onClick(View v) {
        Window window = getWindow();
        View view = window.getDecorView();

        int viewId = v.getId();
        int visibility = 0;

        switch (viewId) {
            case R.id.button_hide_navigation:
                //一時的にナビゲーションバーを非表示
                visibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                break;
            case R.id.button_low_profile:
                //ナビゲーションコントロールを暗くする
                visibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
                break;
            case R.id.button_visible:
                //ナビゲーションバー及びコントロールを表示
                visibility = View.SYSTEM_UI_FLAG_VISIBLE;
                break;
        }

        view.setSystemUiVisibility(visibility);
    }
}
