package com.littglobal.vidcomp.videomanipulation;

public class ResolutionAlone {

   public static final ResolutionAlone RESOLUTION_1080P = new ResolutionAlone( 1920, 1080 );
   public static final ResolutionAlone RESOLUTION_720P = new ResolutionAlone( 1280, 720 );
   public static final ResolutionAlone RESOLUTION_480P = new ResolutionAlone( 740, 480 );
   public static final ResolutionAlone RESOLUTION_360P = new ResolutionAlone( 640, 360 );
   public static final ResolutionAlone RESOLUTION_QVGA = new ResolutionAlone( 320, 240 );
   public static final ResolutionAlone RESOLUTION_QCIF = new ResolutionAlone( 176, 144 );

   int mWidth;
   int mHeight;

   public ResolutionAlone(int width, int height ) {
      mWidth = width;
      mHeight = height;
   }

   public int getWidth() {
      return mWidth;
   }

   public void setWidth( int width ) {
      mWidth = width;
   }

   public int getHeight() {
      return mHeight;
   }

   public void setHeight( int height ) {
      mHeight = height;
   }

   public ResolutionAlone rotate() {
      return new ResolutionAlone( mHeight, mWidth );
   }

}
