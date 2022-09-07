package utilites;

import android.content.Context;
import android.widget.Toast;

import com.example.barcodeconcept.CodeItem;
import com.example.barcodeconcept.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SerializationObj implements Serializable {

    FileOutputStream fos;
    ObjectOutputStream os;
    FileInputStream fis;
    ObjectInputStream ois;
    ArrayList<CodeItem> read_list;
    File fichier;
    Context contxt;

    public SerializationObj(Context contexte) {
        this.contxt = contexte;
        //fichier=new File("sauvegarde.dj");
        ArrayList<CodeItem> initialiseur = new ArrayList<>();
        initialiseur.add(new CodeItem(0, "1"));
        writeFile(initialiseur);
        System.out.println("first print succceed");
        Toast.makeText(contxt, "Serialization obj created", Toast.LENGTH_LONG);
    }

    public void writeFile(ArrayList<CodeItem> listCodes) {
        try {
            fos = new MainActivity().getContext().openFileOutput("sauvegarde.dj", Context.MODE_PRIVATE);
            os = new ObjectOutputStream(fos);
            os.writeObject(listCodes);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(contxt, "Fichier introuvable", Toast.LENGTH_LONG);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(contxt, "Probleme de flux", Toast.LENGTH_LONG);
        } finally {
            try {
                Toast.makeText(contxt, "closing function", Toast.LENGTH_SHORT);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<CodeItem> readFile() {

        Toast.makeText(contxt, fichier.getName() + " exists", Toast.LENGTH_SHORT);
        try {
            fis = new MainActivity().getContext().openFileInput("sauvegarde.dj");
            ois = new ObjectInputStream(fis);
            try {
                read_list = (ArrayList<CodeItem>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return read_list;
    }

}
