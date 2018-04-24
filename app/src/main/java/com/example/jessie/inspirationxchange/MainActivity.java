package com.example.jessie.inspirationxchange;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.homeRecyclerView)
    RecyclerView mHomeRecyclerView;

    @BindView(R.id.emptyTextView)
    TextView mEmptyTextView;

    protected static final Query sInspirationQuery =
            FirebaseDatabase.getInstance().getReference().child("Inspirations");

    private final static int RC_LOGIN = 4659;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        mHomeRecyclerView.setHasFixedSize(true);
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // already signed in
            attachRvAdapter();
        } else {
            // not signed in
            userNotSignedIn();
        }
        attachRvAdapter();

    }

    private void userNotSignedIn() {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setTosUrl("https://jessiejburton.com/index.php/ietos/")
                .setPrivacyPolicyUrl("https://jessiejburton.com/index.php/iepp/")
                .build(), RC_LOGIN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_LOGIN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // if successful login
            if(resultCode == RESULT_OK) {
                //startActivity();
                //startActivity(SignedInActivity.createIntent(this, response));
                //startActivity(data);
                finish();
                //sign in failed
            }  else {
                if (response == null) {
                    //toast if user pressed back button during login
                    Toast.makeText(this, "Not signed in!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "No Internet Access!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(this, "Sign in Error!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        Drawable addInspirationDrawable = menu.findItem(R.id.addInspiration).getIcon();
        Drawable userAccountDrawable = menu.findItem(R.id.userAccount).getIcon();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.addInspiration) {
            String className = "com.example.jessie.inspirationxchange.CreateActivity";
            nextActivity(className);
        } else if(id == R.id.userAccount) {
            String className = "com.example.jessie.inspirationxchange.UserActivity";
            nextActivity(className);
        }
        return super.onOptionsItemSelected(item);
    }

    public void nextActivity(String className) {
        Intent intent = null;
        try {
            intent = new Intent(this, Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

    public void attachRvAdapter() {
        RecyclerView.Adapter adapter = newAdapter();
        mHomeRecyclerView.setAdapter(adapter);
    }



    protected RecyclerView.Adapter newAdapter() {
        FirebaseRecyclerOptions<Inspiration> options =
                new FirebaseRecyclerOptions.Builder<Inspiration>()
                        .setQuery(sInspirationQuery, Inspiration.class)
                        .setLifecycleOwner(this)
                        .build();

        return new FirebaseRecyclerAdapter<Inspiration, CardViewHolder>(options) {
            @Override
            public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new CardViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.inspiration_card, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull CardViewHolder holder, int position, @NonNull Inspiration model) {
                holder.bind(model);
            }


            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mEmptyTextView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
            }
        };
    }
}
