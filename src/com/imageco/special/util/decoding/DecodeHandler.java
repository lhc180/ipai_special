/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.imageco.special.util.decoding;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.imageco.special.R;

import com.imageco.special.itake.activityImpl.ActivityPhoto;
import com.imageco.special.util.camera.CameraManager;
import com.imageco.special.util.camera.PlanarYUVLuminanceSource;

import java.util.Hashtable;

/**
 * Class DecodeHandler ...
 *
 * @author Administrator
 *         Created on 11-10-22
 */
final class DecodeHandler extends Handler {

    /**
     * Field TAG
     */
    private static final String TAG = DecodeHandler.class.getSimpleName();

    /**
     * Field activityImpl
     */
    private final ActivityPhoto activityPhoto;
    /**
     * Field multiFormatReader
     */
    private final MultiFormatReader multiFormatReader;

    /**
     * Constructor DecodeHandler creates a new DecodeHandler instance.
     *
     * @param activityPhoto of type ActivityPhoto
     * @param hints    of type Hashtable<DecodeHintType, Object>
     */
    DecodeHandler(ActivityPhoto activityPhoto, Hashtable<DecodeHintType, Object> hints) {
        multiFormatReader = new MultiFormatReader();
        multiFormatReader.setHints(hints);
        this.activityPhoto = activityPhoto;
    }

    /**
     * Method handleMessage ...
     *
     * @param message of type Message
     */
    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case R.id.decode:
                //Log.d(TAG, "Got decode message");
                decode((byte[]) message.obj, message.arg1, message.arg2);
                break;
            case R.id.quit:
                Looper.myLooper().quit();
                break;
        }
    }

    /**
     * Decode the data within the viewfinder rectangle, and time how long it took. For efficiency,
     * reuse the same reader objects from one decode to the next.
     *
     * @param data   The YUV preview frame.
     * @param width  The width of the preview frame.
     * @param height The height of the preview frame.
     */
    private void decode(byte[] data, int width, int height) {
        long start = System.currentTimeMillis();
        Result rawResult = null;
        PlanarYUVLuminanceSource source = CameraManager.get().buildLuminanceSource(data, width, height);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            rawResult = multiFormatReader.decodeWithState(bitmap);
        } catch (ReaderException re) {
            // continue
        } finally {
            multiFormatReader.reset();
        }

        if (rawResult != null) {
            long end = System.currentTimeMillis();
            Log.d(TAG, "Found barcode (" + (end - start) + " ms):\n" + rawResult.toString());
            Message message = Message.obtain(activityPhoto.getHandler(), R.id.decode_succeeded, rawResult);
            Bundle bundle = new Bundle();
            bundle.putParcelable(DecodeThread.BARCODE_BITMAP, source.renderCroppedGreyscaleBitmap());
            message.setData(bundle);
            //Log.d(TAG, "Sending decode succeeded message...");
            message.sendToTarget();
        } else {
            Message message = Message.obtain(activityPhoto.getHandler(), R.id.decode_failed);
            message.sendToTarget();
        }
    }

}
