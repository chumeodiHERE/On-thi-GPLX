package com.hltstudio.on_thi_gplx.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class imageConverter {
    String imageData;

    public imageConverter(String imageData) {
        this.imageData = imageData;
    }

    public Bitmap imageConverted()
    {
        String base64Image = imageData.split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
