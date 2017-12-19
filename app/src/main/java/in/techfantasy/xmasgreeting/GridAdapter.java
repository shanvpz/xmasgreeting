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

public class GridAdapter extends BaseAdapter {
    private Context Context;
    int[] images;
    public GridAdapter(Context c, int[] images) {
        this.images=images;
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
        View view1;
        view1=inflater.inflate(R.layout.dailogsticker,null);
        ImageView imageView=view1.findViewById(R.id.images);
        imageView.setImageResource(images[i]);
        return view1;
    }

}
