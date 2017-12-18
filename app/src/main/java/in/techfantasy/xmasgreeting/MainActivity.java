package in.techfantasy.xmasgreeting;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // final LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final FrameLayout canvas = (FrameLayout) findViewById(R.id.canvasView);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
             int i=0;
            @Override
            public void onClick(View view) {
                i=i+1;
                final StickerTextView sv=new StickerTextView(MainActivity.this);
                sv.setText("Demo Text");
                sv.setId(i);
                //sv.setLayoutParams(params);
                canvas.addView(sv);
            }
        });



// add a stickerImage to canvas
        StickerImageView iv_sticker = new StickerImageView(MainActivity.this);
        iv_sticker.setImageDrawable(getResources().getDrawable(R.drawable.c10));
        canvas.addView(iv_sticker);

// add a stickerText to canvas
        final StickerTextView tv_sticker = new StickerTextView(MainActivity.this);
        tv_sticker.setText("nkDroid");
        canvas.addView(tv_sticker);


        canvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_sticker.setControlItemsHidden(true);
            }
        });
        canvas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                tv_sticker.setControlItemsHidden(false);
                tv_sticker.setTextColor(Color.RED);
                return true;
            }
        });

    }
}
