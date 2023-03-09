package utilites;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.barcodeconcept.MainActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateurCodeQR {
    private EditText editText;
    private ImageView imgView;
    private MainActivity activity;

    public GenerateurCodeQR() {
    }

    public Bitmap genererInterne(String texte) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(texte, BarcodeFormat.QR_CODE, 300, 150);
            BarcodeEncoder b_encoder = new BarcodeEncoder();
             return b_encoder.createBitmap(matrix);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            Log.i("Information", "Veuillez contacter le devellopeur de l'application");
        }
        return null;
    }
    public Bitmap genererBarcode(String texte) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(texte, BarcodeFormat.CODE_128, 300, 150);
            BarcodeEncoder b_encoder = new BarcodeEncoder();
            return b_encoder.createBitmap(matrix);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            Log.i("Information", "Veuillez contacter le devellopeur de l'application");
        }
        return null;
    }

    public GenerateurCodeQR(EditText text, ImageView imgv, MainActivity actv) {
        imgView = imgv;
        activity = actv;
        editText = text;
    }

    public void generer(String texte) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(texte, BarcodeFormat.QR_CODE, 300, 300);
            BarcodeEncoder b_encoder = new BarcodeEncoder();
            Bitmap bitmap = b_encoder.createBitmap(matrix);
            imgView.setImageBitmap(bitmap);
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            Log.i("Information", "The Null pointer exception again");
        }
    }
}
