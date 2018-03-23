package com.example.jessie.inspirationxchange;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jessie on 2/7/2018.
 */

public class CardViewHolder extends RecyclerView.ViewHolder {

    private final TextView mCardTitle;
    private final TextView mCardAuthor;
    private final TextView mCardBody;

    public CardViewHolder(View itemView) {
        super(itemView);
        mCardTitle = itemView.findViewById(R.id.cardTitle);
        mCardAuthor = itemView.findViewById(R.id.cardAuthor);
        mCardBody = itemView.findViewById(R.id.cardBody);
    }

    public void bind(Inspiration inspiration) {
        setTitle(inspiration.getTitle());
        setBody(inspiration.getBody());
        setAuthor(inspiration.getAuthor());
    }

    private void setAuthor(String author) {
        mCardAuthor.setText(author);
    }

    private void setBody(String body) {
        mCardBody.setText(body);
    }

    public void setTitle(String title) {
        mCardTitle.setText(title);
    }


}
