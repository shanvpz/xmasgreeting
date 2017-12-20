package in.techfantasy.xmasgreeting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DefaultActivity extends AppCompatActivity {
    final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    boolean doubleBackToExitPressedOnce = false;
GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        if (Build.VERSION.SDK_INT < 23) {
            //your code here
        } else {
            requestruntimePermission();
        }


        gridview = findViewById(R.id.gridbackground);

        gridview.setAdapter(new BackgroundGridAdapter(DefaultActivity.this, new DataStore().background));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intmain = new Intent(DefaultActivity.this, MainActivity.class);
                intmain.putExtra("backgroundkey", i);
                startActivity(intmain);
                finish();
            }
        });
    }

    private void requestruntimePermission() {

        int hasCameraPermission = ActivityCompat.checkSelfPermission(DefaultActivity.this, Manifest.permission.CAMERA)+
                ActivityCompat.checkSelfPermission(DefaultActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)+
                ActivityCompat.checkSelfPermission(DefaultActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DefaultActivity.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            Toast.makeText(DefaultActivity.this, "Runtime Permission is already granted", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                // Check if the only required permission has been granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Runtime Permission is Granted", Toast.LENGTH_SHORT).show();
                } else {
                    LinearLayout layout;
                    layout=findViewById(R.id.defaultlayout);

                    Snackbar.make(layout, "Please enable permission from settings",
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", DefaultActivity.this.getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please Click BACK Again To Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 500);
    }
}
