package com.example.jessie.inspirationxchange;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateActivity extends AppCompatActivity {

    private static final String TAG = "InspirationExchange";

    @BindView(R.id.createSubmitButton)
    Button mSubmitButton;

    @BindView(R.id.createTitle)
    EditText mCreateTitle;

    @BindView(R.id.createBody)
    MultiAutoCompleteTextView mCreateBody;



    private String[] mCategory = {"Attitude", "Character", "Courage", "Happiness", "Love",
            "Motivational", "Opportunity", "Perseverance"};

    protected List<String> tempCategoriesChose = new ArrayList<>();

    protected static final Query sQuery =
            FirebaseDatabase.getInstance().getReference().child("Inspirations");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);



    }

    @OnClick(R.id.createSubmitButton)
    public void submitClicked(){
        String title = mCreateTitle.getText().toString().trim();
        String body = mCreateBody.getText().toString().trim();
        String author = "User1";
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
            createDialog(title, body, author);
        } else {
            Toast.makeText(CreateActivity.this,
                    "Please add Title and complete Story before moving on",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void createDialog(final String title, final String body, final String author) {
        final Inspiration inspiration = new Inspiration();
        tempCategoriesChose.clear();
        final AlertDialog.Builder dialog = new AlertDialog.Builder(CreateActivity.this);
        dialog.setTitle("Choose Categories");
        dialog.setMultiChoiceItems(mCategory, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if(isChecked){
                    if(!tempCategoriesChose.contains(mCategory[position])) {
                        tempCategoriesChose.add(mCategory[position]);
                    }
                } else if (tempCategoriesChose.contains(mCategory[position])) {
                    tempCategoriesChose.remove(mCategory[position]);
                }
            }
        });

        dialog.setCancelable(false);
        dialog.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //inspiration.setCategoriesChose(tempCategoriesChose);

                for (String st:tempCategoriesChose) {
                    if(st.equals("Attitude")) {
                        inspiration.setAttitude(true);
                    } else if(st.equals("Character")) {
                        inspiration.setCharacter(true);
                    } else if(st.equals("Courage")) {
                        inspiration.setCourage(true);
                    } else if(st.equals("Happiness")) {
                        inspiration.setHappiness(true);
                    } else if(st.equals("Love")) {
                        inspiration.setLove(true);
                    } else if(st.equals("Motivational")) {
                        inspiration.setMotivational(true);
                    } else if(st.equals("Opportunity")) {
                        inspiration.setOpportunity(true);
                    } else if(st.equals("Perseverance")) {
                        inspiration.setPerseverance(true);
                    }
                }
                inspiration.setTitle(title);
                inspiration.setAuthor(author);
                inspiration.setBody(body);
                onSendData(inspiration);
            }
        });

        dialog.show();
    }


    public void onSendData(Inspiration inspiration) {
        sQuery.getRef().push().setValue(inspiration, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null) {
                    Log.e(TAG, "Failed to write message", databaseError.toException());
                }
            }
        });
    }
}
