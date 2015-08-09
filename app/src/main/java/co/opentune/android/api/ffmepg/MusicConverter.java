package co.opentune.android.api.ffmepg;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import co.opentune.android.singleton.OpenTuneApplication;

/**
 * Created by Gavin on 8/8/2015.
 */
public class MusicConverter {

    private static FFmpeg ffmpeg = null;
    private static OpenTuneApplication openTuneApplication;

    private static FFmpeg getInstance(OpenTuneApplication openTuneApplication) {
        if (ffmpeg == null) {
            ffmpeg = FFmpeg.getInstance(openTuneApplication);
            loadBinary();
            MusicConverter.openTuneApplication = openTuneApplication;
        }
        return ffmpeg;
    }

    public static void ToAAC( String inputVideoPath, String outputAudioPath, MusicConverterCallback callback) throws MusicConverterException, MusicConverterException {
        String cmd = "-i " + inputVideoPath + " -strict -2 " + outputAudioPath;
        RunMPEGCommand(cmd, callback);
    }


    private static void loadBinary() {
        try {
            FFmpeg ffmpeg = getInstance(openTuneApplication);
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



    public static void RunMPEGCommand(String command, MusicConverterCallback callback) throws MusicConverterException {
        try {
            ffmpeg.execute(command, (ExecuteBinaryResponseHandler) callback);
        } catch (Exception ex) {
            // Handle if FFmpeg is not supported by device
            throw new MusicConverterException(ex.getMessage());
        }
    }
}
