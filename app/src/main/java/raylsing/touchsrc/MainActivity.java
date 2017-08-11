package raylsing.touchsrc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import raylsing.widget.MyFloatAB;

public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";

    @BindView(R.id.fab) MyFloatAB fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Intent startIntent = new Intent(this, MyService.class);
//        startService(startIntent);
        fab.setImageResource(R.drawable.icon_sfdog);
    }

    @OnClick(R.id.fab)
    public void onFabClick(View v){
        Log.e(TAG, "onClick: 1" );
    }


}
