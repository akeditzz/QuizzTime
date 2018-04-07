package com.akeditzz.quizztime.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Akshay on 25-03-2018.
 */

public class ImageHelper {

    /**
     * ImageHelper class to load blurr images in the background
     */

    private static ImageHelper instance;
    private Context context;


    private ImageHelper() {
    }

    /**
     * Method to get instance of this class
     * @return
     */
    public static ImageHelper getInstance() {
        if (instance == null) {
            instance = new ImageHelper();
        }
        return instance;
    }

    // Compressing the image twice to get more blurr effect and also to maintain memory

    /**
     * Method to compress image
     * @param context
     * @param image
     * @return
     */
    public Bitmap getCompressedImage(Context context, int image) {
        this.context = context;
        Bitmap bitmapMaster = compressBitmap(BitmapFactory.decodeResource(context.getResources(), image));
        int height = (int) (bitmapMaster.getHeight() * 0.3);
        int width = (int) (bitmapMaster.getWidth() * 0.3);

        return decodeSampledBitmapFromResource(context.getResources(), image, width, height);

    }

    /**
     * Method to compress image
     * @param bitmap
     * @return
     */
    private Bitmap compressBitmap(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 5, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

    }

    /**
     * Method to get sample bitmap
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * Method to calculate the best compress size
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
