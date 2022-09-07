package utilites;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.widget.Toast;

import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.InnerResultCallback;
import com.sunmi.peripheral.printer.SunmiPrinterService;


import static android.widget.Toast.LENGTH_SHORT;

public class PrintingService {
    String inner_text;
    Context inner_context;
    InnerPrinterCallback innerPrinterCallback1;
    Bitmap bitmap;
    boolean result;
    GenerateurCodeQR gencode;

        public PrintingService(String text_to_print, Context ctxt){
        this.inner_text=text_to_print;
        this.inner_context=ctxt;
        gencode=new GenerateurCodeQR();
        this.bitmap=gencode.genererInterne(text_to_print);
        innerPrinterCallback1=new InnerPrinterCallback() {
            @Override
            protected void onConnected(SunmiPrinterService service) {
                try {
                    Toast.makeText(inner_context,"Impression reussie!", LENGTH_SHORT).show();
                    service.printBitmap(bitmap, new InnerResultCallback() {
                        @Override
                        public void onRunResult(boolean isSuccess) throws RemoteException {
                            Toast.makeText(inner_context,"Succes!", LENGTH_SHORT).show();
                        }

                        @Override
                        public void onReturnString(String result) throws RemoteException {
                            Toast.makeText(inner_context,"Valeur texte retournee!", LENGTH_SHORT).show();
                        }

                        @Override
                        public void onRaiseException(int code, String msg) throws RemoteException {
                            Toast.makeText(inner_context,"Probleme! appelez le devellopeur", LENGTH_SHORT).show();
                        }

                        @Override
                        public void onPrintResult(int code, String msg) throws RemoteException {
                            Toast.makeText(inner_context,"Cinquieme etape!", LENGTH_SHORT).show();
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onDisconnected() {
                Toast.makeText(inner_context,"Imprimante deconnectee", LENGTH_SHORT).show();
            }
        };
    }

    public void imprimer(){
        try {
            result = InnerPrinterManager.getInstance().bindService(inner_context,innerPrinterCallback1);
        } catch (InnerPrinterException e) {
            e.printStackTrace();
            Toast.makeText(inner_context,"Erreur dans le processus", LENGTH_SHORT).show();
        }
    }
}
