package in.techfantasy.xmasgreeting;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.skydoves.colorpickerview.ColorListener;
import com.skydoves.colorpickerview.ColorPickerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    FloatingActionMenu fam;
    FloatingActionButton btnText,btnGreetings,btnSticker,btnImage,btnShare,btnAbout;
    Button btn;
    FrameLayout canvas;
    Dialog dialog;
    ArrayList<StickerTextView> stickerTextViewArrayList=new ArrayList<StickerTextView>();
    ArrayList<StickerImageView> stickerImageViewArrayList=new ArrayList<StickerImageView>();
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
            int textColor= Color.BLACK;
            String font;
            TextView txtAnnie,txtalco,txtabril,txtalmen,txtOk;
            @Override
            public void onClick(View view) {

                fam.close(true);
                dialog.setContentView(R.layout.dailogtext);
                final EditText etxtText=dialog.findViewById(R.id.etxtTextAdd);
                txtabril=dialog.findViewById(R.id.txtAbril);
                txtalco=dialog.findViewById(R.id.txtAlco);
                txtalmen=dialog.findViewById(R.id.txtAlmen);
                txtAnnie=dialog.findViewById(R.id.txtAnnie);
                txtOk=dialog.findViewById(R.id.txtSubmit);
                ColorPickerView cpv=dialog.findViewById(R.id.colorPickerView);
                cpv.setColorListener(new ColorListener() {
                    @Override
                    public void onColorSelected(int color) {
                        etxtText.setTextColor(color);
                        textColor=color;
                    }
                });
                txtabril.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        font="abril_fatface.ttf";
                    }
                });
                txtAnnie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        font="annie_use_your_telescope.ttf";
                    }
                });
                txtalmen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        font="almendra_sc.ttf";
                    }
                });
                txtalco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        font="aclonica.ttf";
                    }
                });
                dialog.show();


                txtOk.setOnClickListener(new View.OnClickListener() {
                    int i=0;

                    @Override
                    public void onClick(View view) {
                        i++;
                        final StickerTextView stv=new StickerTextView(MainActivity.this);
                        stv.setText(etxtText.getText().toString());
                        stv.setTextColor(textColor);
                        stv.setId(i);
                        //Typeface tf = Typeface.createFromAsset(MainActivity.this.getAssets(), font);
                        //stv.setTypeFace(tf);
                        stv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                stv.setControlItemsHidden(false);
                            }
                        });
                        stickerTextViewArrayList.add(stv);
                        canvas.addView(stv);
                        dialog.cancel();
                    }
                });
//                i=i+1;
//                final StickerTextView sv=new StickerTextView(MainActivity.this);
//                sv.setText("Demo Text");
//                sv.setId(i);
//
//                //sv.setLayoutParams(params);
                //canvas.addView(sv);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fam.close(true);
            }
        });


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fam.close(true);
            }
        });


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fam.close(true);
            }
        });

        btnSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fam.close(true);
                final int[] iarray=new DataStore().stickers;
                dialog.setContentView(R.layout.gridsticker);
                GridView gridView=dialog.findViewById(R.id.gridsticker);
                gridView.setAdapter(new GridAdapter(MainActivity.this,new DataStore().stickers));
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        StickerImageView siv=new StickerImageView(MainActivity.this);
                        siv.setImageDrawable(getResources().getDrawable(iarray[i]));
                        stickerImageViewArrayList.add(siv);
                        canvas.addView(siv);
                        dialog.cancel();
                    }
                });
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


        canvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(StickerTextView stv:stickerTextViewArrayList){
                    stv.setControlItemsHidden(true);
                }
                for(StickerImageView siv:stickerImageViewArrayList){
                    siv.setControlItemsHidden(true);
                }
            }
        });
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
