package com.example.barcodeconcept;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;
import java.util.ArrayList;
import utilites.GenerateurCodeQR;
import utilites.GenererCode;
import utilites.PrintingService;

public class ListCodes extends Activity {

    ListView listView;
    GenerateurCodeQR code_gen;
    Toolbar barreOutils;
    Context centralContext;
    CodeItemAdapter arrayAdapter;
    ArrayList<CodeItem> codeGenerateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_codes);
        mapContent();
        setActionBar(barreOutils);
        barreOutils.setTitle("Liste des codes imprimés");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        settingAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                final AlertDialog dialogue = new AlertDialog.Builder(centralContext)
                        .setTitle("Confirmation")
                        .setMessage("Voulez-vous imprimer le code: "+codeGenerateur.get(position).codePrinted)
                        .setPositiveButton("Oui", null)
                        .setNegativeButton("Annuler", null)
                        .show();
                Button btnDialogue = dialogue.getButton(AlertDialog.BUTTON_POSITIVE);
                btnDialogue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new PrintingService(codeGenerateur.get(position).codePrinted, centralContext).imprimer();
                        dialogue.dismiss();
                    }
                });
            }
        });
    }

    void mapContent() {
        code_gen = new GenerateurCodeQR();

        listView = findViewById(R.id.codes_list);
        barreOutils = findViewById(R.id.toolbar);
        this.centralContext = this;
    }

    public void settingAdapter() {
        codeGenerateur = new GenererCode(centralContext).GetItems();
        arrayAdapter = new CodeItemAdapter(centralContext, R.layout.list_item,
                codeGenerateur, this);
        listView.setAdapter(arrayAdapter);
    }
}