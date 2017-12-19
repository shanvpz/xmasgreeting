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

public class StickerAdapter extends BaseAdapter {
    private Context Context;
    public StickerAdapter(Context c) {

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
    public int[] images={R.drawable.sticker1,R.drawable.sticker2,R.drawable.sticker3,
                         R.drawable.stcker4,R.drawable.sticker5,R.drawable.sticker6,
                         R.drawable.sticker7,R.drawable.sticker9,R.drawable.sticker8,
                         R.drawable.sticker10,R.drawable.sticker11,
                         R.drawable.sticker13,R.drawable.sticker14, R.drawable.sticker15,
                         R.drawable.sticker16,R.drawable.sticker17,R.drawable.sticker18,
                         R.drawable.sticker19,R.drawable.sticker20,R.drawable.sticker21,
                         R.drawable.sticker22,R.drawable.sticker23,R.drawable.sticker24,
                         R.drawable.sticker25,R.drawable.sticker26,R.drawable.sticker27,
                         R.drawable.sticker28,R.drawable.sticker29,R.drawable.sticker30,
                         R.drawable.sticker31,R.drawable.sticker33,R.drawable.sticker34,
                         R.drawable.sticker36,R.drawable.sticker37,R.drawable.sticker38,
                         R.drawable.sticker39,

                         };
}
