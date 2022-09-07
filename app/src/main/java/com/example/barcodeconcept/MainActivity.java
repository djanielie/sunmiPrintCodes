package com.example.barcodeconcept;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import utilites.GenerateurCodeQR;
import utilites.GenererCode;
import utilites.PrintingService;
import static android.widget.Toast.LENGTH_SHORT;
public class MainActivity extends Activity {

    Button button;
    ImageView imgView;
    EditText editText;
    TextView warning_message;
    GenerateurCodeQR gencode;
    Button goToListBtn, generer_aleatoire;
    public static Context context;
    PrintingService printer;
    String texteImprimable = null;
    GenererCode fichiers;
    //AlertDialog Adialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toutInitialiser();
        gencode = new GenerateurCodeQR(editText, imgView, this);
        imgView.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().isEmpty()) {
                    gencode.generer(editText.getText().toString().trim());
                    texteImprimable = editText.getText().toString().trim();
                    imgView.setEnabled(true);
                } else {
                    warning_message.setText("Veuillez remplir le champs de texte");
                    dialogueMessage("Le champs est vide", "Veuillez inserer un code à 8 caracteres");
                }
            }
        });
        goToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(MainActivity.this, ListCodes.class);
                nextIntent.putExtra("Value_sent", "Greetings from the main activity");
                startActivity(nextIntent);
            }
        });

        generer_aleatoire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(new GenererCode(context).getIdentifiant());
            }
        });
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    fichiers = new GenererCode(context);
                    final AlertDialog dialogue = new AlertDialog.Builder(context)
                            .setTitle("Confirmation")
                            .setMessage("Voulez-vous imprimer le code: " + texteImprimable)
                            .setPositiveButton("Oui", null)
                            .setNegativeButton("Annuler", null)
                            .show();
                    Button positif = dialogue.getButton(AlertDialog.BUTTON_POSITIVE);
                    positif.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            printer = new PrintingService(texteImprimable, getApplicationContext());
                            printer.imprimer();
                            fichiers.ajouterElement(texteImprimable);
                            editText.setText(null);
                            dialogue.dismiss();

                        }
                    });


            }
        });
    }
    public String getTextInserted() {
        return editText.getText().toString().trim();
    }

    public Context getContext() {
        return context;
    }


    public void dialogueMessage(String titre, String message) {
        final AlertDialog dialogue = new AlertDialog.Builder(context)
                .setTitle(titre)
                .setMessage(message)
                .setCancelable(true)
                .show();
    }

    public void toutInitialiser() {
        button = findViewById(R.id.button_generer);
        imgView = findViewById(R.id.view_img);
        editText = findViewById(R.id.inserted);
        warning_message = findViewById(R.id.warning_message);
        goToListBtn = findViewById(R.id.button_liste);
        generer_aleatoire = findViewById(R.id.button_print);
        context = this;
    }
}