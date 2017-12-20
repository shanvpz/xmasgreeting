package in.techfantasy.xmasgreeting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.skydoves.colorpickerview.ColorListener;
import com.skydoves.colorpickerview.ColorPickerView;

/**
 * Created by campusiq on 19/12/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    String[] greetings;
    Context ctx;
    Dialog d;
    public CardAdapter(Context ctx,String[] greetings){
        this.greetings=greetings;
        this.ctx=ctx;
    }


    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardgreeting,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        holder.tv.setText(greetings[position]);

    }

    @Override
    public int getItemCount() {
        return greetings.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ColorPickerView cpv;
        int textColor= Color.BLACK;
        public ViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textcard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    d=new Dialog(ctx);
                    d.setContentView(R.layout.dailogtext);
                    final EditText etxtText=d.findViewById(R.id.etxtTextAdd);

                    etxtText.setText(tv.getText().toString());
                    final TextView tv=d.findViewById(R.id.txtSubmit);
                    cpv=d.findViewById(R.id.colorPickerView);
                    cpv.setColorListener(new ColorListener() {
                        @Override
                        public void onColorSelected(int color) {
                            etxtText.setTextColor(color);
                            textColor=color;
                        }
                    });
                    tv.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            MainActivity.addGreet(etxtText.getText().toString(),ctx,textColor);
                            d.cancel();
                        }
                    });
                    d.show();

                }
            });


        }
    }
}
