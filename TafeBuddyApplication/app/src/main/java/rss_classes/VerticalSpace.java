package rss_classes;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by timot on 10/25/2016.
 */

//this will handle the spacing between the card views


public class VerticalSpace extends RecyclerView.ItemDecoration {



        int Space;


    public VerticalSpace(int space) {
        Space = space;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = Space;
        outRect.bottom = Space;
        outRect.right = Space;

        if(parent.getChildLayoutPosition(view)==0){
            outRect.top=Space;
        }



    }
}
