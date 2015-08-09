package co.opentune.android.api;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

/**
 * Created by Gavin on 8/8/2015.
 */
public class MusicConverter {

    private static FFmpeg _ffmpeg = null;
    
    public static void ToAAC(Context context, String videoSource, String outputPath, MusicConverterCallback callback) throws MusicConverterException, MusicConverterException {
        String cmd = "-i " + videoSource + " -strict -2 " + outputPath;
        RunMPEGCommand(context, cmd, callback);
    }


    public static void loadBinary(Context context) {
        try {
            FFmpeg ffmpeg = getInstance(context);
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    //showUnsupportedExceptionDialog();
                }
            });
        } catch (FFmpegNotSupportedException e) {
            //showUnsupportedExceptionDialog();
        }
    }

    private static FFmpeg getInstance(Context context) {
        if (_ffmpeg == null) {
            _ffmpeg = FFmpeg.getInstance(context);
        }
        return _ffmpeg;
    }

    public static void RunMPEGCommand(Context context, String command,
                                      MusicConverterCallback callback) throws MusicConverterException {
        FFmpeg ffmpeg = getInstance(context);
        try {
            ffmpeg.execute(command, (ExecuteBinaryResponseHandler) callback);
        } catch (Exception ex) {
            // Handle if FFmpeg is not supported by device
            throw new MusicConverterException(ex.getMessage());
        }
    }
}


//    public static void RunMPEGCommand(Context context,String command, final MP3ConverterCallback callback)
//    {
//        FFmpeg ffmpeg=getInstance(context);
//        try {
//            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
//                @Override
//                public void onStart() {
//                    callback.onStart();
//                }
//
//                @Override
//                public void onFailure() {
//                    callback.onFailure();
//                }
//
//                @Override
//                public void onSuccess() {
//                    callback.on
//                }
//
//                @Override
//                public void onFinish() {}
//            });
//        } catch (FFmpegNotSupportedException e) {
//            // Handle if FFmpeg is not supported by device
//        }
//    }
