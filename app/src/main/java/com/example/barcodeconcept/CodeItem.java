package com.example.barcodeconcept;

import android.graphics.Bitmap;
import android.widget.Button;

public class CodeItem {

    int numberPrinted;

    String codePrinted;

    Bitmap bitMapImage;

    Button boutton;

    public Button getBoutton() {

        return boutton;
    }

    public void setBoutton(Button boutton) {
        this.boutton = boutton;
    }
    public CodeItem() {
    }
    public CodeItem(int numberPrinted, String codePrinted) {
        this.numberPrinted = numberPrinted;
        this.codePrinted = codePrinted;
    }

    public CodeItem(int numberPrinted, String codePrinted, Bitmap bitMapImage) {
        this.numberPrinted = numberPrinted;
        this.codePrinted = codePrinted;
        this.bitMapImage = bitMapImage;
    }
    public CodeItem(int numberPrinted, String codePrinted, Bitmap bitMapImage,Button paramButton) {
        this.numberPrinted = numberPrinted;
        this.codePrinted = codePrinted;
        this.bitMapImage = bitMapImage;
        this.boutton=paramButton;
    }

    public Bitmap getBitMapImage() {
        return bitMapImage;
    }

    public void setBitMapImage(Bitmap bitMapImage) {
        this.bitMapImage = bitMapImage;
    }

    public int getNumberPrinted() {
        return numberPrinted;
    }

    public void setNumberPrinted(int numberPrinted) {
        this.numberPrinted = numberPrinted;
    }

    public String getCodePrinted() {
        return codePrinted;
    }

    public void setCodePrinted(String codePrinted) {
        this.codePrinted = codePrinted;
    }

    public String getNumberPrintedString() {
        return String.valueOf(numberPrinted);
    }


}
