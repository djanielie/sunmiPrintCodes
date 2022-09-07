package utilites;

import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    ImageView imageView;
    TextView CodePrinted;
    TextView NtextView;
    public Button buttonDelete;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setImageViewD(Bitmap imageView) {
        this.imageView.setImageBitmap(imageView);
    }

    public TextView getCodePrinted() {
        return CodePrinted;
    }

    public void setCodePrinted(TextView codePrinted) {
        this.CodePrinted = codePrinted;
    }

    public void setCodePrintedD(String codePrinted) {
        this.CodePrinted.setText(codePrinted);
    }

    public TextView getNtextView() {
        return NtextView;
    }

    public void setNtextView(TextView ntextView) {
        NtextView = ntextView;
    }

    public void setNtextViewD(String ntextView) {
        NtextView.setText(ntextView);
    }

    public Button getButtonDelete() {
        return buttonDelete;
    }

    public void setButtonDelete(Button buttonDelete) {
        this.buttonDelete = buttonDelete;
    }

}
