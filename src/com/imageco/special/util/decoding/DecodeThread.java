/*
 * Copyright (C) 2008 ZXing authors
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

import android.os.Handler;
import android.os.Looper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import com.imageco.special.itake.activityImpl.ActivityPhoto;

import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * This thread does all the heavy lifting of decoding the images.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
final class DecodeThread extends Thread {

    /**
     * Field BARCODE_BITMAP
     */
    public static final String BARCODE_BITMAP = "barcode_bitmap";

    /**
     * Field activityImpl
     */
    private final ActivityPhoto activityPhoto;
    /**
     * Field hints
     */
    private final Hashtable<DecodeHintType, Object> hints;
    /**
     * Field handler
     */
    private Handler handler;
    /**
     * Field handlerInitLatch
     */
    private final CountDownLatch handlerInitLatch;

    /**
     * Constructor DecodeThread creates a new DecodeThread instance.
     *
     * @param activityPhoto            of type ActivityPhoto
     * @param decodeFormats       of type Vector<BarcodeFormat>
     * @param characterSet        of type String
     * @param resultPointCallback of type ResultPointCallback
     */
    DecodeThread(ActivityPhoto activityPhoto,
                 Vector<BarcodeFormat> decodeFormats,
                 String characterSet,
                 ResultPointCallback resultPointCallback) {

        this.activityPhoto = activityPhoto;
        handlerInitLatch = new CountDownLatch(1);

        hints = new Hashtable<DecodeHintType, Object>(3);

//    // The prefs can't change while the thread is running, so pick them up once here.
//    if (decodeFormats == null || decodeFormats.isEmpty()) {
//      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activityImpl);
//      decodeFormats = new Vector<BarcodeFormat>();
//      if (prefs.getBoolean(PreferencesActivity.KEY_DECODE_1D, true)) {
//        decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
//      }
//      if (prefs.getBoolean(PreferencesActivity.KEY_DECODE_QR, true)) {
//        decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
//      }
//      if (prefs.getBoolean(PreferencesActivity.KEY_DECODE_DATA_MATRIX, true)) {
//        decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
//      }
//    }
        if (decodeFormats == null || decodeFormats.isEmpty()) {
            decodeFormats = new Vector<BarcodeFormat>();
            decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);

        }

        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);

        if (characterSet != null) {
            hints.put(DecodeHintType.CHARACTER_SET, characterSet);
        }

        hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, resultPointCallback);
    }

    /**
     * Method getHandler returns the handler of this DecodeThread object.
     *
     * @return the handler (type Handler) of this DecodeThread object.
     */
    Handler getHandler() {
        try {
            handlerInitLatch.await();
        } catch (InterruptedException ie) {
            // continue?
        }
        return handler;
    }

    /**
     * Method run ...
     */
    @Override
    public void run() {
        Looper.prepare();
        handler = new DecodeHandler(activityPhoto, hints);
        handlerInitLatch.countDown();
        Looper.loop();
    }

}
