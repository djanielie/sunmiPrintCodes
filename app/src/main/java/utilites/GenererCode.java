package utilites;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.barcodeconcept.CodeItem;
import java.util.ArrayList;
import java.util.Random;

public class GenererCode {

    CodeItem codeItem;
    ArrayList<CodeItem> liste;
    DbHelper db;
    Random aleatoire;
    String identifiant;
    Context mainContext;
    char[] lettres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

       public GenererCode(Context context) {
        mainContext = context;
        aleatoire = new Random();
        db = new DbHelper(context);
        identifiant = NanoIdUtils.randomNanoId(aleatoire, lettres, 8);
    }
    public ArrayList<CodeItem> GetItems() {
        ArrayList<CodeItem> contenu = new ArrayList<CodeItem>();
        Cursor cursor = db.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(mainContext, "Il n'y a aucun code dans la base de donnée", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                Button individualBtn = new Button(mainContext);
                individualBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                contenu.add(new CodeItem(Integer.valueOf(cursor.getString(1)), cursor.getString(0),
                        new GenerateurCodeQR().genererInterne(cursor.getString(0)), individualBtn));
            }
        }
        return contenu;
    }
    public String getIdentifiant() {
        return this.identifiant;
    }

    public void ajouterElement(String coderecorded) {
        codeItem = new CodeItem(1, coderecorded);
        liste = new ArrayList<CodeItem>();
        liste.add(codeItem);
        if (db.insertCode(coderecorded, 1) == true) {
            Toast.makeText(mainContext, "Insertion succeeded", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mainContext, "Insertion failed", Toast.LENGTH_LONG).show();
        }
    }
}
