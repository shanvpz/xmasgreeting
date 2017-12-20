package in.techfantasy.xmasgreeting;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.skydoves.colorpickerview.ColorListener;
import com.skydoves.colorpickerview.ColorPickerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    FloatingActionMenu fam;
    FloatingActionButton btnText,btnGreetings,btnSticker,btnImage,btnShare,btnAbout;
    Button btn;
    static FrameLayout canvas;
    static Dialog dialog;
    ImageView bgiv;
    static int greetcount=0;
    ArrayList<StickerTextView> stickerTextViewArrayList=new ArrayList<StickerTextView>();
    ArrayList<StickerImageView> stickerImageViewArrayList=new ArrayList<StickerImageView>();
    static ArrayList<StickerTextView> greetArrayList=new ArrayList<StickerTextView>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initialize();
       // final LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        



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

                //dialog.setContentView();

                canvas.setDrawingCacheEnabled(true);
                canvas.buildDrawingCache();
                Bitmap bitmap = canvas.getDrawingCache();
                Uri uri;
                try {
                    File file = new File(getBaseContext().getExternalFilesDir("Temp"), "file.png");
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    uri=Uri.fromFile(file);
                    final PackageManager pm = MainActivity.this.getPackageManager();
                    final Intent sendIntent = new Intent(Intent.ACTION_SEND);

                    sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
                    sendIntent.setType("image/jpeg");
                    List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                    List<LabeledIntent> intentList = new ArrayList<>();

                    for (int i = 0; i < resInfo.size(); i++) {
                        ResolveInfo ri = resInfo.get(i);
                        String packageName = ri.activityInfo.packageName;
                        final Intent intent = new Intent();
                        intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                        intent.setPackage(packageName);
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_STREAM,uri);
                        intent.setType("image/jpeg");
                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.getIconResource()));
                    }

                    for(int i=0;i < intentList.size();i++){
                        LabeledIntent lin=intentList.get(i);

                        if(lin.getPackage().equals("com.whatsapp")){
                            LabeledIntent temp=intentList.get(1);
                            intentList.remove(1);
                            intentList.add(1,lin);
                            intentList.remove(i);
                            intentList.add(temp);
                        }

                        if(lin.getPackage().equals("com.instagram.android")){
                            LabeledIntent temp=intentList.get(2);
                            intentList.remove(2);
                            intentList.add(2,lin);
                            intentList.remove(i);
                            intentList.add(temp);
                        }
                    }

                    intentList.add((LabeledIntent) getSaveToGalleryIntent(MainActivity.this,uri));
                    Intent openInChooser = Intent.createChooser(intentList.remove(0), "Share");
                    LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                    startActivity(openInChooser);

                fam.close(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                gridView.setAdapter(new BackgroundGridAdapter(MainActivity.this,new DataStore().stickers));
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
                dialog.setContentView(R.layout.dailoggreeting);
                RecyclerView rv=dialog.findViewById(R.id.greetingrecycle);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(new CardAdapter(MainActivity.this,new DataStore().greetings));
                dialog.show();
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
                fam.close(true);
                for(StickerTextView stv:stickerTextViewArrayList){
                    stv.setControlItemsHidden(true);
                }
                for(StickerImageView siv:stickerImageViewArrayList){
                    siv.setControlItemsHidden(true);
                }
                for (StickerTextView stvg:greetArrayList){
                    stvg.setControlItemsHidden(true);
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
        intent=getIntent();
        int bgkey=intent.getIntExtra("backgroundkey",0);
        int bgid=new DataStore().background[bgkey];
        bgiv=findViewById(R.id.bgImageView);
        bgiv.setImageDrawable(getResources().getDrawable(bgid));
//        canvas.setBackground(getResources().getDrawable(bgid));
    }
    public static void addGreet(String msg,Context ctx,int textColor){

        greetcount++;
        final StickerTextView stv=new StickerTextView(ctx);
        stv.setText(msg);
        stv.setTextColor(textColor);
        stv.setId(greetcount);
        //Typeface tf = Typeface.createFromAsset(MainActivity.this.getAssets(), font);
        //stv.setTypeFace(tf);
        stv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stv.setControlItemsHidden(false);
            }
        });
        greetArrayList.add(stv);
        canvas.addView(stv);
        dialog.cancel();
    }

    private Intent getSaveToGalleryIntent(final Context context, final Uri imgUri) {
        final Intent intent = new Intent(context,SaveToGalleryActivity.class);
        intent.putExtra("savetogallery", imgUri.toString());
        return new LabeledIntent(intent, BuildConfig.APPLICATION_ID,
                "Save to gallery",android.R.drawable.ic_menu_save );
    }
}
