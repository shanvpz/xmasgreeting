package in.techfantasy.xmasgreeting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by campusiq on 19/12/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    String[] greetings;
    Context ctx;
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
        public ViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textcard);
        }
    }
}
