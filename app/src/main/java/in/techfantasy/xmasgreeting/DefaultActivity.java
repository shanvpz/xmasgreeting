package in.techfantasy.xmasgreeting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class DefaultActivity extends AppCompatActivity {
TextView txtgal;
GridView grdview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        txtgal.findViewById(R.id.textViewgallery);
        grdview.findViewById(R.id.gridbackground);
    }
}
