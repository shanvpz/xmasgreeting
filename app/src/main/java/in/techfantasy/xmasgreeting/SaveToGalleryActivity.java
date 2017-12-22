package in.techfantasy.xmasgreeting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveToGalleryActivity extends AppCompatActivity {
Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_gallery);
        in=getIntent();
        Uri u= Uri.parse(in.getStringExtra("savetogallery"));
        try {
            File direct = new File(Environment.getExternalStorageDirectory() + "/ChristmasWishes");

            if (!direct.exists()) {
                File newDirectory = new File(Environment.getExternalStorageDirectory()+"/ChristmasWishes");
                newDirectory.mkdirs();
            }

            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory()+"/ChristmasWishes/"+fileName+".jpg");
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(SaveToGalleryActivity.this.getContentResolver(),u);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "File saved sucessfully\n"+direct, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}
