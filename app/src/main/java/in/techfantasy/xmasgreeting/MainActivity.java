package in.techfantasy.xmasgreeting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.skydoves.colorpickerview.ColorListener;
import com.skydoves.colorpickerview.ColorPickerView;

import java.io.File;
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
    ArrayList<StickerImageView> imagesArrayList = new ArrayList<StickerImageView>();
    Intent intent;
    private Uri picUri;
    final int CROP_PIC = 2;
    boolean doubleBackToExitPressedOnce = false;
    String TempFileName="ChristmasWishes.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                        if(etxtText.getText().toString().equals("")){
                            Toast.makeText(MainActivity.this,"Please enter text",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            stickerTextViewArrayList.add(stv);
                            canvas.addView(stv);
                            dialog.cancel();
                        }
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
                dialog=new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.dailogabout);
                TextView email=dialog.findViewById(R.id.textViewemail);
                final CardView devCard=dialog.findViewById(R.id.devCard);
                CardView cardBulb=dialog.findViewById(R.id.cardBulb);
                devCard.setVisibility(View.INVISIBLE);
                Linkify.addLinks(email,Linkify.EMAIL_ADDRESSES);

                cardBulb.setOnClickListener(new View.OnClickListener() {
                    boolean visible=false;
                    @Override
                    public void onClick(View view) {

                        if(visible==false) {
                            devCard.setVisibility(View.VISIBLE);
                            visible=true;
                        }
                        else {
                            devCard.setVisibility(View.INVISIBLE);
                            visible=false;
                        }
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unselect();
                    new TestTask().execute();
                //dialog.setContentView();

//                canvas.setDrawingCacheEnabled(true);
//                canvas.buildDrawingCache();
//                Bitmap bitmap = canvas.getDrawingCache();
//                Uri uri;
//                try {
//                    File file = new File(getBaseContext().getExternalFilesDir("Temp"), "file.png");
//                    FileOutputStream fOut = new FileOutputStream(file);
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//                    fOut.flush();
//                    fOut.close();
//                    uri=Uri.fromFile(file);
//                    final PackageManager pm = MainActivity.this.getPackageManager();
//                    final Intent sendIntent = new Intent(Intent.ACTION_SEND);
//
//                    sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
//                    sendIntent.setType("image/jpeg");
//                    List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
//                    List<LabeledIntent> intentList = new ArrayList<>();
//
//                    for (int i = 0; i < resInfo.size(); i++) {
//                        ResolveInfo ri = resInfo.get(i);
//                        String packageName = ri.activityInfo.packageName;
//                        final Intent intent = new Intent();
//                        intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
//                        intent.setPackage(packageName);
//                        intent.setAction(Intent.ACTION_SEND);
//                        intent.putExtra(Intent.EXTRA_STREAM,uri);
//                        intent.setType("image/jpeg");
//                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.getIconResource()));
//                    }
//
//                    for(int i=0;i < intentList.size();i++){
//                        LabeledIntent lin=intentList.get(i);
//
//                        if(lin.getPackage().equals("com.whatsapp")){
//                            LabeledIntent temp=intentList.get(1);
//                            intentList.remove(1);
//                            intentList.add(1,lin);
//                            intentList.remove(i);
//                            intentList.add(temp);
//                        }
//
//                        if(lin.getPackage().equals("com.instagram.android")){
//                            LabeledIntent temp=intentList.get(2);
//                            intentList.remove(2);
//                            intentList.add(2,lin);
//                            intentList.remove(i);
//                            intentList.add(temp);
//                        }
//                    }
//
//                    intentList.add((LabeledIntent) getSaveToGalleryIntent(MainActivity.this,uri));
//                    Intent openInChooser = Intent.createChooser(intentList.remove(0), "Share");
//                    LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
//                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
//                    startActivity(openInChooser);
//
//                fam.close(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

        });


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fam.close(true);
                startActivityForResult(getPickImageChooserIntent(),123);
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
                fam.close(true);
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
                unselect();
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

    public void unselect(){
        for(StickerTextView stv:stickerTextViewArrayList){
            stv.setControlItemsHidden(true);
        }
        for(StickerImageView siv:stickerImageViewArrayList){
            siv.setControlItemsHidden(true);
        }
        for (StickerTextView stvg:greetArrayList){
            stvg.setControlItemsHidden(true);
        }
        for (StickerImageView sivi:imagesArrayList){
            sivi.setControlItemsHidden(true);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            File file = new File(getBaseContext().getExternalFilesDir("Temp"), TempFileName);
            file.delete();
        }catch (Exception E){

        }
    }


    public void initialize(){
        btnText=findViewById(R.id.btnText);
        btnGreetings=findViewById(R.id.btnGreetings);
        btnSticker=findViewById(R.id.btnSticker);
        btnImage=findViewById(R.id.btnImage);
        btnShare=findViewById(R.id.btnSaveShare);
        btnAbout=findViewById(R.id.btnAbout);
        bgiv = findViewById(R.id.bgImageView);
        fam=findViewById(R.id.menu);
        canvas = (FrameLayout) findViewById(R.id.canvasView);
        dialog=new Dialog(this);
        intent=getIntent();
        Uri uri=null;
        try {
            uri = Uri.parse(intent.getStringExtra("bgfromgallery"));

        }
        catch (Exception e){

        }
        int bgkey=intent.getIntExtra("backgroundkey",0);
        if(uri!=null){

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, null);
            bgiv.setImageBitmap(bitmap);
        }
        else {
            int bgid = new DataStore().background[bgkey];
            bgiv.setImageDrawable(getResources().getDrawable(bgid));
        }

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

    public Intent getPickImageChooserIntent() {

// Determine Uri of camera image to  save.
       // Uri outputFileUri =  getCaptureImageOutputUri();

        List<Intent> allIntents = new  ArrayList<>();
        PackageManager packageManager =  getPackageManager();

// collect all camera intents
//        Intent captureIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        List<ResolveInfo> listCam =  packageManager.queryIntentActivities(captureIntent, 0);
//        for (ResolveInfo res : listCam) {
//            Intent intent = new  Intent(captureIntent);
//            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//            intent.setPackage(res.activityInfo.packageName);
//            if (outputFileUri != null) {
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//            }
//            allIntents.add(intent);
//        }

// collect all gallery intents
        Intent galleryIntent = new  Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery =  packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new  Intent(galleryIntent);
            intent.setComponent(new  ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

// the main intent is the last in the  list (fucking android) so pickup the useless one
        Intent mainIntent =  allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if  (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity"))  {
                mainIntent = intent;
                break;
            }
        }

        //Intent removeintent=new Intent(getContext(),com.campusiq.manappuramdesign.MainActivity.class);
        //removeintent.putExtra("key","removepic");
        //allIntents.add(removeintent);
        allIntents.remove(mainIntent);

// Create a chooser from the main  intent
        Intent chooserIntent =  Intent.createChooser(mainIntent, "Select source");

// Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,  allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }



    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new  File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }


    public Uri getPickImageResultUri(Intent  data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null  && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ?  getCaptureImageOutputUri() : data.getData();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK&&requestCode==123) {
            final Uri imageUri = getPickImageResultUri(data);


            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

            } catch (IOException e) {
                e.printStackTrace();


            }
            dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.croplayout);
            final CropImageView mCropView = (CropImageView) dialog.findViewById(R.id.cropImageView);
            TextView btnCrop = dialog.findViewById(R.id.btnCrop);
            mCropView.setCropMode(CropImageView.CropMode.SQUARE);

            //mCropView.load(imageUri).executeAsCompletable();
            mCropView.setImageBitmap(bitmap);
            btnCrop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCropView.crop(imageUri)
                            .execute(new CropCallback() {
                                @Override
                                public void onSuccess(Bitmap cropped) {
                                    StickerImageView siv = new StickerImageView(MainActivity.this);
                                    siv.setImageBitmap(cropped);
                                    imagesArrayList.add(siv);
                                    canvas.addView(siv);
                                    dialog.cancel();
                                    File getImage = getExternalCacheDir();
                                    getImage.delete();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    dialog.cancel();
                                    Toast.makeText(MainActivity.this,"Unsupported file format.",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });


            dialog.show();
        }
        else{
            //Toast.makeText(MainActivity.this,"Operation Cancelled",Toast.LENGTH_SHORT).show();

        }


    }



    @Override
    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Press one more time to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 1900);
        //super.onBackPressed();
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Going Back?").setMessage("All your current edits will be lost, and you will return to home screen.").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this,DefaultActivity.class));
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }



    ////////////////////////////////
    private class TestTask extends AsyncTask<Void, Void, Integer> {
        ProgressDialog pd=new ProgressDialog(MainActivity.this);

        @Override
        protected Integer doInBackground(Void... voids) {
            canvas.setDrawingCacheEnabled(true);
            canvas.buildDrawingCache();
            Bitmap bitmap = canvas.getDrawingCache();
            Uri uri;
            try {
                File file = new File(getBaseContext().getExternalFilesDir("Temp"), TempFileName);
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

            return 1;
        }

        protected void onPreExecute() {
            //show dialog
            pd.setTitle("Loading...");
            pd.setMessage("Please be patient...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setIndeterminate(true);
            pd.show();
        }



        protected void onPostExecute(Integer result) {
            //cancel dialog
            pd.cancel();
        }
    }
}
