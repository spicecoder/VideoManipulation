package com.littglobal.vidcomp.videomanipulation;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;


public class ResampleInBackgroundTask  extends AsyncTask<Uri, Void, Uri> {
    ResolutionAlone mOutputResolution = ResolutionAlone.RESOLUTION_1080P;
    int mOutputBitRate;
    int mOutputFrameRate;
    int mOutputIFrameInterval;
    //new ResampleAloneTask().execute(url1, url2, url3);
    public void setmOutputResolution(ResolutionAlone mOutputResolution) {
        this.mOutputResolution = mOutputResolution;
    }

    public void setmOutputBitRate(int mOutputBitRate) {
        this.mOutputBitRate = mOutputBitRate;
    }

    public void setmOutputFrameRate(int mOutputFrameRate) {
        this.mOutputFrameRate = mOutputFrameRate;
    }

    public void setmOutputIFrameInterval(int mOutputIFrameInterval) {
        this.mOutputIFrameInterval = mOutputIFrameInterval;
    }

    @Override
    protected Uri doInBackground( Uri... uris ) {

        if ( uris.length < 2 ) {
            return null;
        }

        Uri inputUri = uris[0];
        Uri outputUri = uris[1];

        VideoResamplerAlone resampler = new VideoResamplerAlone();
        resampler.addSamplerClip( new SamplerClipAlone( inputUri ) );
        // resampler.setInput( inputUri );
        resampler.setOutput( outputUri );

        resampler.setOutputResolution( mOutputResolution.getWidth(), mOutputResolution.getHeight() );
        resampler.setOutputBitRate( mOutputBitRate );
        resampler.setOutputFrameRate( mOutputFrameRate );
        resampler.setOutputIFrameInterval( mOutputIFrameInterval );

        try {
            resampler.start();
        } catch ( Throwable e ) {
            e.printStackTrace();
        }

        return outputUri;
    }

    @Override
    protected void onPostExecute( Uri outputUri ) {
        //new uri for the resampled video passed into foreground
        Intent sendIntent = new Intent();
        sendIntent.setAction( Intent.ACTION_VIEW );
        sendIntent.setDataAndType( outputUri, "video/mp4" );

       // outputUri.
        // startActivity( sendIntent );

    }

    public void  setLittTransfer() {


    }
}
