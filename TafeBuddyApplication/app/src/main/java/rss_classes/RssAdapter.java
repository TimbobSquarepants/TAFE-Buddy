package rss_classes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import programmingsolutions.tafebuddy.R;
import programmingsolutions.tafebuddy.WebviewActivity;

/**
 * Created by 000993549 on 24/10/2016.
 */




public class RssAdapter extends RecyclerView.Adapter<RssAdapter.MyViewHolder> {
    ArrayList<FeedItem>feedItems;
    Context context;


    public RssAdapter(Context context,ArrayList<FeedItem>feedItems){
        this.feedItems=feedItems;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_rss_feed,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    //this is what data and animations will be shown on the card that is displayed.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //this will add animations to the cards you can see the list of animations at
        //https://github.com/daimajia/AndroidViewAnimations

        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        final FeedItem current=feedItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());

        //if there where images we can get it here and put it in a image view on the card view
        //to show.
        //Picasso.with(context).load(current.getThumbnailUrl()).into(holder.Thumbnail);


        //this is where we set the onclick listener for the cards.
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override

            //at the moment im just sending it to our webview will have to look at sending it
            //to custom tabs as the tafe website is abit average at handling web view.
            public void onClick(View v) {
                Intent intent = new Intent(context,WebviewActivity.class);
                intent.putExtra("extra.url",current.getLink());
                context.startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {
        try {
            return feedItems.size();
        }
        catch (Exception e){
            System.out.println("Error in List");
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,Date;
        ImageView Thumbnail;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.card_title);
            Description= (TextView) itemView.findViewById(R.id.card_description);
            Date= (TextView) itemView.findViewById(R.id.card_date);
            //Thumbnail= (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView= (CardView) itemView.findViewById(R.id.custom_card_view);
        }
    }
}

