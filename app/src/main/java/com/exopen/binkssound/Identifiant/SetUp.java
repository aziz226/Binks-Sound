package com.exopen.binkssound.Identifiant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.exopen.binkssound.AlertClass.Dialog;
import com.exopen.binkssound.AlertClass.ErrorAlert;
import com.exopen.binkssound.AlertClass.FlagIntent;
import com.exopen.binkssound.AlertClass.LoadingBar;
import com.exopen.binkssound.Class.Constant;
import com.exopen.binkssound.Class.User;
import com.exopen.binkssound.MainActivity;
import com.exopen.binkssound.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetUp extends AppCompatActivity {

    private TextInputEditText name, aname, city;
    private Button btn;
    private LoadingBar bar;
    private FirebaseAuth mAuth;
    private DatabaseReference uRef;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        name= findViewById(R.id.name);
        aname= findViewById(R.id.artist_name);
        city= findViewById(R.id.ville);
        btn= findViewById(R.id.terminate);

        bar= new LoadingBar(this);
        mAuth= FirebaseAuth.getInstance();
        uid= mAuth.getCurrentUser().getUid();
        uRef= FirebaseDatabase.getInstance().getReference().child(Constant.user);

        btn.setOnClickListener(v -> {
            String n= name.getText().toString().trim();
            String an= aname.getText().toString().trim();
            String ci= city.getText().toString().trim();

            if (TextUtils.isEmpty(n)){
                name.setError(getString(R.string.veuilllez_entrer_votre_nom_et_pr_nom));
                new ErrorAlert(this, getString(R.string.veuilllez_entrer_votre_nom_et_pr_nom));
            } else if (TextUtils.isEmpty(an)) {
                name.setError(getString(R.string.veuilllez_entrer_votre_nom_d_artiste));
                new ErrorAlert(this, getString(R.string.veuilllez_entrer_votre_nom_d_artiste));
            } else if (TextUtils.isEmpty(ci)) {
                name.setError(getString(R.string.veuilllez_entrer_votre_ville));
                new ErrorAlert(this, getString(R.string.veuilllez_entrer_votre_ville));
            }else {
                bar.Show();
                User user= new User(n, an, ci, "");
                uRef.child(uid).setValue(user)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                bar.Dismiss();
                                new FlagIntent(this, MainActivity.class);
                            }else {
                                bar.Dismiss();
                                new Dialog(this, getString(R.string.une_erreur_s_est_produit_veuillez_reessayer));
                            }
                        });
            }
        });

    }
}