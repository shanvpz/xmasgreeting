package in.techfantasy.xmasgreeting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class DefaultActivity extends AppCompatActivity {

GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        gridview=findViewById(R.id.gridbackground);


        gridview.setAdapter(new BackgroundGridAdapter(DefaultActivity.this,new DataStore().background));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intmain=new Intent(DefaultActivity.this,MainActivity.class);
                intmain.putExtra("backgroundkey",i);
                startActivity(intmain);
                finish();
            }
        });




    }
}
