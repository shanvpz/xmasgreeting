package in.techfantasy.xmasgreeting;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.skydoves.colorpickerview.ColorListener;
import com.skydoves.colorpickerview.ColorPickerView;



public class MainActivity extends AppCompatActivity {
    FloatingActionMenu fam;
    FloatingActionButton btnText,btnGreetings,btnSticker,btnImage,btnShare,btnAbout;
    Button btn;
    FrameLayout canvas;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initialize();
       // final LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        


        btn=findViewById(R.id.button);
        btnText.setOnClickListener(new View.OnClickListener() {
             int i=0;
            @Override
            public void onClick(View view) {
                fam.close(true);
                i=i+1;
                final StickerTextView sv=new StickerTextView(MainActivity.this);
                sv.setText("Demo Text");
                sv.setId(i);

                //sv.setLayoutParams(params);
                canvas.addView(sv);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.gridsticker);
                GridView gridView=dialog.findViewById(R.id.gridsticker);
                gridView.setAdapter(new ImageAdapter(MainActivity.this));
                dialog.show();


            }
        });

        btnGreetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

// add a stickerImage to canvas
//        StickerImageView iv_sticker = new StickerImageView(MainActivity.this);
//        iv_sticker.setImageDrawable(getResources().getDrawable(R.drawable.c10));
//        canvas.addView(iv_sticker);

// add a stickerText to canvas
//        final StickerTextView tv_sticker = new StickerTextView(MainActivity.this);
//        tv_sticker.setText("nkDroid");
//        canvas.addView(tv_sticker);


//        canvas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tv_sticker.setControlItemsHidden(true);
//            }
//        });
//        canvas.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                tv_sticker.setControlItemsHidden(false);
//                tv_sticker.setTextColor(Color.RED);
//                return true;
//            }
//        });


    }
    public void initialize(){
        btnText=findViewById(R.id.btnText);
        btnGreetings=findViewById(R.id.btnGreetings);
        btnSticker=findViewById(R.id.btnSticker);
        btnImage=findViewById(R.id.btnImage);
        btnShare=findViewById(R.id.btnSaveShare);
        btnAbout=findViewById(R.id.btnAbout);
        fam=findViewById(R.id.menu);
        canvas = (FrameLayout) findViewById(R.id.canvasView);
        dialog=new Dialog(this);
    }
}
