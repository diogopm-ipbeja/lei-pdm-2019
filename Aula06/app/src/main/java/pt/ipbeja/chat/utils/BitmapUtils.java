package pt.ipbeja.chat.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.nio.ByteBuffer;

public class BitmapUtils {

    private BitmapUtils() {}

    /**
     * Transforma um Bitmap num array de bytes
     * @param bitmap O Bitmap
     * @return Um array de bytes da imagem
     */
    public static byte[] toBytes(Bitmap bitmap) {
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        return byteBuffer.array();
    }

    /**
     * Cria um Bitmap a partir de um array de bytes
     * @param bytes Os bytes da imagem
     * @return Um Bitmap da imagem ou null se não foi possível converter
     */
    @Nullable
    public static Bitmap fromBytes(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
