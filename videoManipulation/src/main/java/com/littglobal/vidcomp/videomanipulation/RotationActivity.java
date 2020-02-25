/**
   Copyright (c) 2014 Rory Hool
   
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
   
       http://www.apache.org/licenses/LICENSE-2.0
   
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 **/

package com.littglobal.vidcomp.videomanipulation;

import java.io.File;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class RotationActivity extends Activity {

   ImageView mVideoThumbnail;

   TextView mVideoName;

   TextView mVideoRotation;

   Uri mUri;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );

      setContentView( R.layout.activity_rotation );

      mVideoThumbnail = (ImageView) findViewById( R.id.selected_video_thumbnail );

      mVideoName = (TextView) findViewById( R.id.selected_video_name );
      mVideoRotation = (TextView) findViewById( R.id.selected_video_rotation );

      getActionBar().setTitle( "Rotation" );
   }

   @Override
   public void onStart() {
      super.onStart();

      Uri uri = getIntent().getData();

      if ( uri != null ) {
         loadVideoUri( uri );
      }
   }

   public void loadVideoUri( Uri uri ) {

      mUri = uri;

      Bitmap bitmap = MediaHelperAlone.GetThumbnailFromVideo( mUri, 0 );
      mVideoThumbnail.setImageBitmap( bitmap );

      File file = new File( mUri.toString() );

      mVideoName.setText( file.getName() );

      int rotation = MediaHelperAlone.GetRotation( mUri );

      mVideoRotation.setText( String.format( Locale.US, "Current Rotation: %d", rotation ) );


   }

   public void onRotateClicked( View view ) {
      String rotationString = (String) view.getTag();
      int rotation = Integer.parseInt( rotationString );

      Uri rotatedVideoUri = MediaHelperAlone.RotateVideo( mUri, rotation );

      Intent sendIntent = new Intent();
      sendIntent.setAction( Intent.ACTION_VIEW );
      sendIntent.setDataAndType( rotatedVideoUri, "video/mp4" );
      startActivity( sendIntent );
   }

}
