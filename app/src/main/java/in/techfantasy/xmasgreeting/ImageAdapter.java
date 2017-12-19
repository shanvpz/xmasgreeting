package in.techfantasy.xmasgreeting;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import in.techfantasy.xmasgreeting.R;

/**
 * Created by campusiq on 19/12/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context Context;
    public ImageAdapter(Context c) {
        Context = c;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater)Context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view1= view;
        view1=inflater.inflate(R.layout.dailogsticker,null);
        ImageView imageView=view1.findViewById(R.id.images);
        imageView.setImageResource(images[i]);
        return null;
    }
    public int[] images={R.drawable.image1,R.drawable.image2,R.drawable.image3,
                         R.drawable.image4,R.drawable.image5,R.drawable.image6,
                         R.drawable.image7, R.drawable.image8,R.drawable.image9,
                         R.drawable.image10,R.drawable.image11,R.drawable.image12,
                         R.drawable.image13,R.drawable.image14,R.drawable.image15,
                         R.drawable.image16,R.drawable.image17,R.drawable.image18,
                         R.drawable.image19,R.drawable.image20,
                         };
}
