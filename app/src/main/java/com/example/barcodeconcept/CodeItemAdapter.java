package com.example.barcodeconcept;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;
import utilites.DbHelper;
import utilites.ViewHolder;

public class CodeItemAdapter extends ArrayAdapter<CodeItem> {

    Context context;
    private final int p_ressource;
    DbHelper database;
    ListCodes listCodes;
    ViewHolder mainViewH = null;

    public CodeItemAdapter(@NonNull Context context, int resource,
                           ArrayList<CodeItem> objects, ListCodes finaliseur) {
        super(context, resource, objects);
       this.p_ressource = resource;
        this.context = context;
        database = new DbHelper(context);
        this.listCodes = finaliseur;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        mainViewH = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(super.getContext());
            convertView = layoutInflater.inflate(p_ressource, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.setImageView((ImageView) convertView.findViewById(R.id.img_view));
            viewHolder.setImageViewD(Objects.requireNonNull(getItem(position)).getBitMapImage());

            viewHolder.setCodePrinted((TextView) convertView.findViewById(R.id.top_text));
            viewHolder.setCodePrintedD(Objects.requireNonNull(getItem(position)).getCodePrinted());

            viewHolder.setNtextView((TextView) convertView.findViewById(R.id.sub_text));
            viewHolder.setNtextViewD(Objects.requireNonNull(getItem(position)).getNumberPrintedString());

            viewHolder.setButtonDelete((Button) convertView.findViewById(R.id.del_item));
            viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog dialogue = new AlertDialog.Builder(context)
                            .setTitle("Confirmation")
                            .setMessage("Voulez-vous supprimer le code: " +
                                    viewHolder.getCodePrinted().getText())
                            .setPositiveButton("Oui", null)
                            .setNegativeButton("Annuler", null)
                            .show();
                    Button btnDialogue = btnDialogue = dialogue.getButton(AlertDialog.BUTTON_POSITIVE);

                    btnDialogue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                if (viewHolder.getCodePrinted().getText().toString().isEmpty()) {
                                    if (database.deletetnullsCode(viewHolder.getCodePrinted().getText().toString())) {
                                        Toast.makeText(context, "Le code: " + viewHolder.getCodePrinted().getText().toString() +
                                                " null effacé avec succès !", Toast.LENGTH_SHORT).show();
                                        listCodes.settingAdapter();
                                    } else {
                                        Toast.makeText(context, "Echec suppression code vide !", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    if (database.deletetCode(viewHolder.getCodePrinted().getText().toString())) {
                                        Toast.makeText(context, "Le code " +
                                                viewHolder.getCodePrinted().getText().toString() +
                                                " effacé avec succès !", Toast.LENGTH_SHORT).show();
                                        listCodes.settingAdapter();
                                    } else {
                                        Toast.makeText(context, "Echec !", Toast.LENGTH_LONG).show();
                                    }
                                }

                            dialogue.dismiss();
                        }
                    });
                }
            });

            convertView.setTag(viewHolder);

        } else {
            mainViewH = (ViewHolder) convertView.getTag();
            mainViewH.setCodePrintedD(Objects.requireNonNull(getItem(position)).getCodePrinted());
            mainViewH.setImageViewD(Objects.requireNonNull(getItem(position)).getBitMapImage());
            mainViewH.setNtextViewD(Objects.requireNonNull(getItem(position)).getNumberPrintedString());
            mainViewH.setButtonDelete(Objects.requireNonNull(getItem(position)).getBoutton());
        }
        return convertView;
    }


}
