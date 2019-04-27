package com.olex.lionandtiger;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void imageViewOnClick(View lionView) {
        ImageView lionImage = (ImageView) lionView;
        lionImage.setTranslationX(-1000);
        lionImage.setImageResource(R.drawable.tiger);
        lionImage.animate().translationX(0).alpha(1).rotation(3600).setDuration(1000);
    }
}
