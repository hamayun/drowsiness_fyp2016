
package com.example.huniya.portrait;

import java.io.File;
import java.io.IOException;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.objdetect.Objdetect;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.media.FaceDetector;


import android.app.Activity;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.media.FaceDetector.Face;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.opencv.core.CvType;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends Activity implements CvCameraViewListener2 {
    private static final String TAG = "OCVSample::Activity";

    public static final int JAVA_DETECTOR = 0;
    private static final int TM_SQDIFF = 0;
    private static final int TM_SQDIFF_NORMED = 1;
    private static final int TM_CCOEFF = 2;
    private static final int TM_CCOEFF_NORMED = 3;
    private static final int TM_CCORR = 4;
    private static final int TM_CCORR_NORMED = 5;

    private String[] mDetectorName;

    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button back;
    Button button4;




    ImageView img23;
    private CameraView OpenCvCamera;
    private boolean mIsJavaCamera = true;
    private MenuItem mItemSwitchCamera = null;
    private File mCascadeFile;
    private CascadeClassifier mJavaDetector;
    private CascadeClassifier mJavaDetectorEye;
    private CascadeClassifier mMouthDetector;

    private MenuItem mItemFace50;
    private MenuItem mItemFace40;
    private MenuItem mItemFace30;
    private MenuItem mItemFace20;
    private MenuItem mItemType;
    // matrix for zooming
    private Mat mZoomWindow;
    private Mat mZoomWindow2;

    private SeekBar mMethodSeekbar;
    private TextView mValue;
    TextView tvOut;
    private Mat mRgba;
    private Mat mGray;
    Mat eye = new Mat();

    private float mRelativeFaceSize = 0.2f;
    private int mAbsoluteFaceSize = 0;
    private static final Scalar FACE_RECT_COLOR = new Scalar(0, 255, 0, 255);


    double xCenter = -1;
    double yCenter = -1;

    private int learn_frames = 0;
    private Mat teplateR;
    private Mat teplateL;
    int method = 0;

    Rect r = new Rect();
    Bitmap bm;
    Bitmap image;

    private static final int MAX_FACES = 3;
    double angle = 0;
    int loop = 0;
    //int k = 0;
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    public double angle_array[] = new double[30];
    public int mouth_array[] = new int[30];
    public int eye_array[] = new int[30];
    public double drowsy_array[] = new double[30];
    int counter = 0;
    int countm = 0;
    int counte = 1;
    int countd=0;
    int open_eye = 0;
    int text=0;
    int eye_open=0;
    float mean=0,prev_eye=0;
    float prob_of_yawn = 0;
    float prob_of_opening = 0;
    float prob_mouth_yawn,prob_yawn_mouth;
    double prob_of_open_eyes;
    double prob_of_close_eyes;
    int open_mouth=0,mouth_count=0;
    int mouth_yawn=0;
    int eye_detect=0;
    int head_detect = 0;
    int head_correct = 0;
    int head_wrong = 0;
    int head_frame = 0;
    int mouth_alert=0;
    int head_alert=0;
    int wrong_frame = 0;
    double prob_head_correct = 0.0;
    double prob_head_wrong = 0.0;
    int yawn_count=0;
    int mouth_detect=0;
    int eyeCount=0;
    int  eye_close = 0;
    int eye_close_frame=0;
    int mouth_warning = 0;
    int head_warning = 0;
    int warning_zone = 0;
    int normal_zone = 0;
    int danger_zone = 0;
    int eye_alert = 0;
    int eye_warning = 0;
    int drowsy_frame=0;
    int head_weightage = 0;
    int mouth_weightage = 0;
    int eye_weightage = 0;
    int check=0;//call the drowsiness function
    double drowsy_measure = 0.0;
    double mouth_factor = 0.0;
    double head_factor = 0.0;
    double eye_factor = 0.0;
    TextView status;
    private int mDetectorType = JAVA_DETECTOR;


    Rect[] facesArray;


    ProgressDialog pDialog;


    MatOfRect faceDetections, eyeDetections;


    private Mat mRgba1;
    private Mat mGray1;
    Bitmap binarized, inverted, imagegray;
    Mat face;
    Mat crop = null;
    ImageView img1, img2, img3, img4;
    Bitmap bitmap1, bitmap2, bitmap3, bitmap4;


    static {


        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error

        }


    }

    private void loadCVResume() {
        BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS: {
                        Log.i(TAG, "OpenCV loaded successfully");


                        try {
                            // load cascade file from application resources
                            InputStream is = getResources().openRawResource(
                                    R.raw.lbpcascade_frontalface);
                            File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
                            mCascadeFile = new File(cascadeDir,
                                    "lbpcascade_frontalface.xml");
                            FileOutputStream os = new FileOutputStream(mCascadeFile);

                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = is.read(buffer)) != -1) {
                                os.write(buffer, 0, bytesRead);
                            }
                            is.close();
                            os.close();

                            // --------------------------------- load left eye
                            // classificator -----------------------------------
                            InputStream iser = getResources().openRawResource(
                                    R.raw.haarcascade_lefteye_2splits);
                            File eyeDir = getDir("cascadeeye",
                                    Context.MODE_PRIVATE);
                            File cascadeFileER = new File(eyeDir,
                                    "haarcascade_lefteye_2splits.xml");
                            FileOutputStream oser = new FileOutputStream(cascadeFileER);

                            byte[] bufferER = new byte[4096];
                            int bytesReadER;
                            while ((bytesReadER = iser.read(bufferER)) != -1) {
                                oser.write(bufferER, 0, bytesReadER);
                            }
                            iser.close();
                            oser.close();

                            // If want only Mouth then use haarcascade_mcs_mouth xml file
                            InputStream mouth = getResources().openRawResource(
                                    R.raw.haarcascade_mcs_mouth);
                            File mouthDir = getDir("cascademouth", Context.MODE_PRIVATE);
                            File mMouthFile = new File(mouthDir,
                                    "haarcascade_mcs_mouth.xml");
                            FileOutputStream os2 = new FileOutputStream(mMouthFile);

                            byte[] buffer2 = new byte[4096];
                            int bytesRead2;
                            while ((bytesRead2 = mouth.read(buffer2)) != -1) {
                                os2.write(buffer2, 0, bytesRead2);
                            }
                            mouth.close();
                            os2.close();

                            mMouthDetector = new CascadeClassifier(mMouthFile.getAbsolutePath());
                            if (mMouthDetector.empty()) {
                                Log.e(TAG, "Failed to load Mouth cascade classifier");
                                mMouthDetector = null;
                            } else {
                                Log.i(TAG,
                                        "Loaded mouth cascade classifier from "
                                                + mMouthFile.getAbsolutePath());
                            }


                            mJavaDetector = new CascadeClassifier(
                                    mCascadeFile.getAbsolutePath());
                            if (mJavaDetector.empty()) {
                                Log.e(TAG, "Failed to load cascade classifier");
                                mJavaDetector = null;
                            } else
                                Log.i(TAG, "Loaded cascade classifier from "
                                        + mCascadeFile.getAbsolutePath());

                            mJavaDetectorEye = new CascadeClassifier(
                                    cascadeFileER.getAbsolutePath());
                            if (mJavaDetectorEye.empty()) {
                                Log.e(TAG, "Failed to load cascade classifier");
                                mJavaDetectorEye = null;
                            } else
                                Log.i(TAG, "Loaded cascade classifier from "
                                        + mCascadeFile.getAbsolutePath());


                            cascadeDir.delete();
                            eyeDir.delete();
                            mouthDir.delete();

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Failed to load cascade. Exception thrown: " + e);
                        }
                        OpenCvCamera.setCameraIndex(1);
                        OpenCvCamera.enableFpsMeter(2);
                        OpenCvCamera.ListenerenableView(1);

                    }
                    break;
                    default: {
                        super.onManagerConnected(status);
                    }
                    break;
                }
            }

        };

        mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
    }

    public MainActivity() {
        mDetectorName = new String[2];
        mDetectorName[JAVA_DETECTOR] = "Java";
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        for (int i = 0; i < 30; i++) {
            angle_array[i] = 0.0;
            mouth_array[i] = 0;
            eye_array[i] = 1;
        }
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.main_activity);
        img23 = (ImageView) findViewById(R.id.img);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        button = (Button) findViewById(R.id.btn);
        button2 = (Button) findViewById(R.id.btn2);
        button3=(Button)findViewById(R.id.btn3);
        button1 = (Button) findViewById(R.id.btn1);
        button4 = (Button) findViewById(R.id.btn4);
        tvOut = (TextView) findViewById(R.id.tvOut);


        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                tvOut.setText("Button OK clicked");


                setContentView(R.layout.activity_hgraph);
                back = (Button) findViewById(R.id.back);
                status = (TextView) findViewById(R.id.status);
                status.setText("Head Angle");
                back.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        tvOut.setText("back button clicked");
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                GraphView line_graph = (GraphView) findViewById(R.id.graph);
                LineGraphSeries<DataPoint> line_series =
                        new LineGraphSeries<DataPoint>(new DataPoint[]{

                                new DataPoint(0, angle_array[0]),
                                new DataPoint(1, angle_array[1]),
                                new DataPoint(2, angle_array[2]),
                                new DataPoint(3, angle_array[3]),
                                new DataPoint(4, angle_array[4]),
                                new DataPoint(5, angle_array[5]),
                                new DataPoint(6, angle_array[6]),
                                new DataPoint(7, angle_array[7]),
                                new DataPoint(8, angle_array[8]),
                                new DataPoint(9, angle_array[9]),
                                new DataPoint(10, angle_array[10]),
                                new DataPoint(11, angle_array[11]),
                                new DataPoint(12, angle_array[12]),
                                new DataPoint(13, angle_array[13]),
                                new DataPoint(14, angle_array[14]),
                                new DataPoint(15, angle_array[15]),
                                new DataPoint(16, angle_array[16]),
                                new DataPoint(17, angle_array[17]),
                                new DataPoint(18, angle_array[18]),
                                new DataPoint(19, angle_array[19]),
                                new DataPoint(20, angle_array[20]),
                                new DataPoint(21, angle_array[21]),
                                new DataPoint(22, angle_array[22]),
                                new DataPoint(23, angle_array[23]),
                                new DataPoint(24, angle_array[24]),
                                new DataPoint(25, angle_array[25]),
                                new DataPoint(26, angle_array[26]),
                                new DataPoint(27, angle_array[27]),
                                new DataPoint(28, angle_array[28]),
                                new DataPoint(29, angle_array[29])


                        });
                line_graph.addSeries(line_series);

                // set the bound

                // set manual X bounds
                line_graph.getViewport().setXAxisBoundsManual(true);
                line_graph.getViewport().setMinX(0);
                line_graph.getViewport().setMaxX(30);

                // set manual Y bounds
                line_graph.getViewport().setYAxisBoundsManual(true);
                line_graph.getViewport().setMinY(0);
                line_graph.getViewport().setMaxY(360);

                line_graph.getViewport().setScrollable(true);


            }

        });

        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                tvOut.setText("Button OK clicked");


                setContentView(R.layout.activity_hgraph);
                back = (Button) findViewById(R.id.back);
                status = (TextView) findViewById(R.id.status);
                status.setText("Eye State");
                back.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        tvOut.setText("back button clicked");
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                System.out.println("  eye.... " + eye_array[0] + " " + eye_array[1] + "" + eye_array[2]);
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

                GraphView line_graph = (GraphView) findViewById(R.id.graph);
                LineGraphSeries<DataPoint> line_series =
                        new LineGraphSeries<DataPoint>(new DataPoint[]{
                                new DataPoint(0, eye_array[0]),
                                new DataPoint(1, eye_array[1]),
                                new DataPoint(2, eye_array[2]),
                                new DataPoint(3, eye_array[3]),
                                new DataPoint(4, eye_array[4]),
                                new DataPoint(5, eye_array[5]),
                                new DataPoint(6, eye_array[6]),
                                new DataPoint(7, eye_array[7]),
                                new DataPoint(8, eye_array[8]),
                                new DataPoint(9, eye_array[9]),
                                new DataPoint(10, eye_array[10]),
                                new DataPoint(11, eye_array[11]),
                                new DataPoint(12, eye_array[12]),
                                new DataPoint(13, eye_array[13]),
                                new DataPoint(14, eye_array[14]),
                                new DataPoint(15, eye_array[15]),
                                new DataPoint(16, eye_array[16]),
                                new DataPoint(17, eye_array[17]),
                                new DataPoint(18, eye_array[18]),
                                new DataPoint(19, eye_array[19]),
                                new DataPoint(20, eye_array[20]),
                                new DataPoint(21, eye_array[21]),
                                new DataPoint(22, eye_array[22]),
                                new DataPoint(23, eye_array[23]),
                                new DataPoint(24, eye_array[24]),
                                new DataPoint(25, eye_array[25]),
                                new DataPoint(26, eye_array[26]),
                                new DataPoint(27, eye_array[27]),
                                new DataPoint(28, eye_array[28]),
                                new DataPoint(29, eye_array[29])


                        });
                line_graph.addSeries(line_series);

                // set the bound

                // set manual X bounds
                line_graph.getViewport().setXAxisBoundsManual(true);
                line_graph.getViewport().setMinX(0);
                line_graph.getViewport().setMaxX(30);

                // set manual Y bounds
                line_graph.getViewport().setYAxisBoundsManual(true);
                line_graph.getViewport().setMinY(0);
                line_graph.getViewport().setMaxY(1);

                line_graph.getViewport().setScrollable(true);


            }

        });
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                tvOut.setText("Button OK clicked");

                setContentView(R.layout.activity_hgraph);

                back = (Button) findViewById(R.id.back);
                status = (TextView) findViewById(R.id.status);
                status.setText("Mouth Opening");
                back.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        tvOut.setText("back button clicked");
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                GraphView line_graph = (GraphView) findViewById(R.id.graph);
                LineGraphSeries<DataPoint> line_series =
                        new LineGraphSeries<DataPoint>(new DataPoint[]{

                                new DataPoint(0, mouth_array[0]),
                                new DataPoint(1, mouth_array[1]),
                                new DataPoint(2, mouth_array[2]),
                                new DataPoint(3, mouth_array[3]),
                                new DataPoint(4, mouth_array[4]),
                                new DataPoint(5, mouth_array[5]),
                                new DataPoint(6, mouth_array[6]),
                                new DataPoint(7, mouth_array[7]),
                                new DataPoint(8, mouth_array[8]),
                                new DataPoint(9, mouth_array[9]),
                                new DataPoint(10, mouth_array[10]),
                                new DataPoint(11, mouth_array[11]),
                                new DataPoint(12, mouth_array[12]),
                                new DataPoint(13, mouth_array[13]),
                                new DataPoint(14, mouth_array[14]),
                                new DataPoint(15, mouth_array[15]),
                                new DataPoint(16, mouth_array[16]),
                                new DataPoint(17, mouth_array[17]),
                                new DataPoint(18, mouth_array[18]),
                                new DataPoint(19, mouth_array[19]),
                                new DataPoint(20, mouth_array[20]),
                                new DataPoint(21, mouth_array[21]),
                                new DataPoint(22, mouth_array[22]),
                                new DataPoint(23, mouth_array[23]),
                                new DataPoint(24, mouth_array[24]),
                                new DataPoint(25, mouth_array[25]),
                                new DataPoint(26, mouth_array[26]),
                                new DataPoint(27, mouth_array[27]),
                                new DataPoint(28, mouth_array[28]),
                                new DataPoint(29, mouth_array[29])


                        });
                line_graph.addSeries(line_series);

                // set the bound

                // set manual X bounds
                line_graph.getViewport().setXAxisBoundsManual(true);
                line_graph.getViewport().setMinX(0);
                line_graph.getViewport().setMaxX(30);

                // set manual Y bounds
                line_graph.getViewport().setYAxisBoundsManual(true);
                line_graph.getViewport().setMinY(0);
                line_graph.getViewport().setMaxY(150);

                line_graph.getViewport().setScrollable(true);


            }

        });


        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                tvOut.setText("Button OK clicked");


                setContentView(R.layout.activity_hgraph);
                back = (Button) findViewById(R.id.back);
                status = (TextView) findViewById(R.id.status);
                status.setText("Drowsiness");
                back.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        tvOut.setText("back button clicked");
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                GraphView line_graph = (GraphView) findViewById(R.id.graph);
                LineGraphSeries<DataPoint> line_series =
                        new LineGraphSeries<DataPoint>(new DataPoint[]{

                                new DataPoint(0, drowsy_array[0]),
                                new DataPoint(1, drowsy_array[1]),
                                new DataPoint(2, drowsy_array[2]),
                                new DataPoint(3, drowsy_array[3]),
                                new DataPoint(4, drowsy_array[4]),
                                new DataPoint(5, drowsy_array[5]),
                                new DataPoint(6, drowsy_array[6]),
                                new DataPoint(7, drowsy_array[7]),
                                new DataPoint(8, drowsy_array[8]),
                                new DataPoint(9, drowsy_array[9]),
                                new DataPoint(10, drowsy_array[10]),
                                new DataPoint(11, drowsy_array[11]),
                                new DataPoint(12, drowsy_array[12]),
                                new DataPoint(13, drowsy_array[13]),
                                new DataPoint(14, drowsy_array[14]),
                                new DataPoint(15, drowsy_array[15]),
                                new DataPoint(16, drowsy_array[16]),
                                new DataPoint(17, drowsy_array[17]),
                                new DataPoint(18, drowsy_array[18]),
                                new DataPoint(19, drowsy_array[19]),
                                new DataPoint(20, drowsy_array[20]),
                                new DataPoint(21, drowsy_array[21]),
                                new DataPoint(22, drowsy_array[22]),
                                new DataPoint(23, drowsy_array[23]),
                                new DataPoint(24, drowsy_array[24]),
                                new DataPoint(25, drowsy_array[25]),
                                new DataPoint(26, drowsy_array[26]),
                                new DataPoint(27, drowsy_array[27]),
                                new DataPoint(28, drowsy_array[28]),
                                new DataPoint(29, drowsy_array[29])


                        });
                line_graph.addSeries(line_series);

                // set the bound

                // set manual X bounds
                line_graph.getViewport().setXAxisBoundsManual(true);
                line_graph.getViewport().setMinX(0);
                line_graph.getViewport().setMaxX(30);

                // set manual Y bounds
                line_graph.getViewport().setYAxisBoundsManual(true);
                line_graph.getViewport().setMinY(0);
                line_graph.getViewport().setMaxY(1);

                line_graph.getViewport().setScrollable(true);


            }

        });
        button4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                tvOut.setText("Button OK clicked");
                check=1;
            }

        });


        if (mIsJavaCamera)
            OpenCvCamera= (CameraView) findViewById(R.id.main_activity);
        else
            OpenCvCamera = (CameraView) findViewById(R.id.main_activity);

        OpenCvCamera.setVisibility(SurfaceView.VISIBLE);

        OpenCvCamera.setCvCameraListener(this);

        mMethodSeekbar = (SeekBar) findViewById(R.id.methodSeekBar);
        mValue = (TextView) findViewById(R.id.method);


        mMethodSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                method = progress;
                switch (method) {
                    case 0:
                        mValue.setText("TM_SQDIFF");
                        break;
                    case 1:
                        mValue.setText("TM_SQDIFF_NORMED");
                        break;
                    case 2:
                        mValue.setText("TM_CCOEFF");
                        break;
                    case 3:
                        mValue.setText("TM_CCOEFF_NORMED");
                        break;
                    case 4:
                        mValue.setText("TM_CCORR");
                        break;
                    case 5:
                        mValue.setText("TM_CCORR_NORMED");
                        break;
                }


            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (OpenCvCamera != null)
            OpenCvCamera.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCVResume();
      /*  new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries

                for (int i = 0; i < 30; i++) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            addEntry();
                            ///k++;
                        }
                    });

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }
                }

            }
        }).start();

*/
    }


    public void onDestroy() {
        super.onDestroy();
        if (OpenCvCamera != null)
           OpenCvCamera.disableView();
    }


    public void onCameraViewStarted(int width, int height) {
        mGray = new Mat();
        mRgba = new Mat();
    }

    public void onCameraViewStopped() {
        mGray.release();
        mRgba.release();
        mZoomWindow.release();
        mZoomWindow2.release();
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        long startTime11 = System.currentTimeMillis();

      //  showNotification();
        mRgba = inputFrame.rgba();
        mGray = inputFrame.gray();

        if (mAbsoluteFaceSize == 0) {
            int height = mGray.rows();
            if (Math.round(height * mRelativeFaceSize) > 0) {
                mAbsoluteFaceSize = Math.round(height * mRelativeFaceSize);
            }

        }
        if (mZoomWindow == null || mZoomWindow2 == null)
            CreateAuxiliaryMats();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MatOfRect faces = new MatOfRect();


                if (mJavaDetector != null)
                    mJavaDetector.detectMultiScale(mGray, faces, 1.1, 2, 2, // TODO: objdetect.CV_HAAR_SCALE_IMAGE
                            new Size(mAbsoluteFaceSize, mAbsoluteFaceSize), new Size());

                eyeMonitoring(mRgba, mGray);

                facesArray = faces.toArray();
                for (int i = 0; i < facesArray.length; i++) {
                    Core.rectangle(mRgba, facesArray[i].tl(), facesArray[i].br(),
                            FACE_RECT_COLOR, 3);
                    xCenter = (facesArray[i].x + facesArray[i].width + facesArray[i].x) / 2;
                    yCenter = (facesArray[i].y + facesArray[i].y + facesArray[i].height) / 2;
                    Point center = new Point(xCenter, yCenter);

                    Core.circle(mRgba, center, 10, new Scalar(255, 0, 0, 255), 3);


                    r = facesArray[i];

                    //Mat head = mRgba.submat(r);
                    bm = Bitmap.createBitmap(mRgba.width(), mGray.height(), Bitmap.Config.RGB_565);
                    image = Bitmap.createBitmap(mRgba.width(), mGray.height(), Bitmap.Config.RGB_565);

                    Utils.matToBitmap(mRgba, bm);
                    Utils.matToBitmap(mRgba, image);
                    img23.setImageBitmap(bm);

                    long startTime = System.currentTimeMillis();
                    new headMonitoring().execute();
                    long endTime = System.currentTimeMillis();
                    long time = endTime - startTime;
                    System.out.println("Time Headdd" + time);

                    //mouth monitoring
                    mouthMonitoring(r);

                    //frame calculation
                    learn_frames++;
                    System.out.println(" *************************************** ");
                    System.out.println("                   Frame is             " + learn_frames);
                    System.out.println(" *************************************** ");



                        drowsinessDetection();


//stuff that updates ui


                }
            }
        });
        long endTime1 = System.currentTimeMillis();
        long Time1=endTime1-startTime11;
        System.out.println("Timeeeeee....." + Time1+"frame is....."+learn_frames );

        return mRgba;
    }


    void mouthMonitoring(Rect r) {

        // compute the eye area
        Rect eyearea = new Rect(r.x + r.width / 8,
                (int) (r.y + (r.height / 4.5)), r.width - 2 * r.width / 8,
                (int) (r.height / 3.0));
        // split it
        Rect eyearea_right = new Rect(r.x + r.width / 16,
                (int) (r.y + (r.height / 4.5)),
                (r.width - 2 * r.width / 16) / 2, (int) (r.height / 3.0));
        Rect eyearea_left = new Rect(r.x + r.width / 16
                + (r.width - 2 * r.width / 16) / 2,
                (int) (r.y + (r.height / 4.5)),
                (r.width - 2 * r.width / 16) / 2, (int) (r.height / 3.0));
        // draw the area - mGray is working grayscale mat, if you want to
        // see area in rgb preview, change mGray to mRgba
        Core.rectangle(mRgba, eyearea_left.tl(), eyearea_left.br(),
                new Scalar(255, 0, 0, 255), 2);
        Core.rectangle(mRgba, eyearea_right.tl(), eyearea_right.br(),
                new Scalar(255, 0, 0, 255), 2);
        teplateR = get_template(mJavaDetectorEye, eyearea_right, 24);
        teplateL = get_template(mJavaDetectorEye, eyearea_left, 24);

        Mat faceROI = mRgba.submat(r);
        MatOfRect mouth = new MatOfRect();
        mMouthDetector.detectMultiScale(faceROI, mouth, 1.1, 2, Objdetect.CASCADE_FIND_BIGGEST_OBJECT | Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30), new Size());


        // cut eye areas and put them to zoom windows
  /*          Imgproc.resize(mRgba.submat(eyearea_left), mZoomWindow2,
                    mZoomWindow2.size());
            Imgproc.resize(mRgba.submat(eyearea_right), mZoomWindow,
                    mZoomWindow.size());*/
        System.out.println(String.format("Detected %s mouth", mouth.toArray().length));


        for (Rect mouth1 : mouth.toArray()) {
            Point center13 = new Point(r.x + mouth1.x + mouth1.width * 0.5,
                    r.y + mouth1.y + mouth1.height * 0.5);
            //        Core.ellipse(mRgba, centre3, new Size(mouth1.width * 0.5,mouth1.height * 0.5), 0, 0, 360,new Scalar(255, 112,43), 4, 8, 0);
            int radius = (int) Math.round(mouth1.width / 2);
            //  Core.circle(mRgba, center13, radius, new Scalar(255, 0, 0), 4, 8, 0);


            MatOfRect lips = new MatOfRect();

            Rect mouthROI = new Rect((int) r.tl().x, (int) r.y + (int) Math.round((r.height / 2)), (int) r.width, (int) Math.round((((int) r.height / 2))));
            // Core.rectangle(mRgba,new Point(mouthROI.x, mouthROI.y),new Point(mouthROI.x + mouthROI.width, mouthROI.y + mouthROI.height), new Scalar(255, 255, 0),2);

            Mat lip_mRgba = mRgba.submat(mouthROI);
            Mat lip_mGrey = mGray.submat(mouthROI);


            for (Rect lip : lips.toArray()) {
                Core.rectangle(lip_mRgba, new Point(lip.x, lip.y), new Point(lip.x + lip.width, lip.y + lip.height), new Scalar(255, 255, 0));

                // System.out.println("here");
                Mat edge_detected = new Mat();

                edge_detected = lip_mGrey;


                Mat pyr = new Mat();
                /*Remove some noise by down/upsampling*/
                Imgproc.pyrUp(pyr, edge_detected, edge_detected.size());
                // Highgui.imwrite("stage0.jpg", edge_detected);


                /*Remove some additional edge noise*/
                 Imgproc.equalizeHist(edge_detected, edge_detected); /*Equalize color histogram*/
                // Highgui.imwrite("stage1.jpg", edge_detected);

                // Highgui.imwrite("stage2.jpg", edge_detected);

                Imgproc.threshold(edge_detected, edge_detected, 125, 255, Imgproc.THRESH_BINARY); /*Create color blobs*/
                //  Highgui.imwrite("stage2a.jpg", edge_detected);
                Mat hierarchy=new Mat();
              Imgproc.erode(edge_detected, edge_detected, new Mat(), new Point(-1, -1), 1); /*Fill in gaps*/
                Imgproc.threshold(edge_detected, edge_detected, 200, 255, Imgproc.THRESH_BINARY); /*Create edge blobs*/

                // Highgui.imwrite("stage3.jpg", edge_detected);

                List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
                for (int x = 0; x < contours.size(); x++) {

                    Imgproc.drawContours(lip_mGrey, contours, x, new Scalar(255, 255, 255, 255), -1, Core.LINE_AA, hierarchy, 0, new Point(lip.x, lip.y));
                }



                System.out.println("dist: width " + lip.width + "  and height  " + lip.height + "   estimated emotion feature: " + ((lip.height >= 50) ? "yawning" : "not yawning"));
                // Highgui.imwrite("stage4.jpg",edge_detected);
                // imshow("lips", edge_detected);
                if (lip.height < 50) {

                }

                System.out.println(learn_frames + "Yawning" + lip.height);
                mouth_array[countm] = lip.height;
                mouth_detect++;
                countm++;
                mouthAnalysis(lip.height);
                if (countm == 30) {

                    countm = 0;
                }

            }
        }
    }



    private Mat get_template(CascadeClassifier clasificator, Rect area, int size) {
        Mat template = new Mat();
        Mat mROI = mGray.submat(area);
        MatOfRect eyes = new MatOfRect();
        Point iris = new Point();
        Rect eye_template = new Rect();
        clasificator.detectMultiScale(mROI, eyes, 1.15, 2,
                Objdetect.CASCADE_FIND_BIGGEST_OBJECT
                        | Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30),
                new Size());

        Rect[] eyesArray = eyes.toArray();
        for (int i = 0; i < eyesArray.length; ) {
            Rect e = eyesArray[i];
            e.x = area.x + e.x;
            e.y = area.y + e.y;
            Rect eye_only_rectangle = new Rect((int) e.tl().x,
                    (int) (e.tl().y + e.height * 0.4), (int) e.width,
                    (int) (e.height * 0.6));
            mROI = mGray.submat(eye_only_rectangle);
            Mat vyrez = mRgba.submat(eye_only_rectangle);


            Core.MinMaxLocResult mmG = Core.minMaxLoc(mROI);

            Core.circle(vyrez, mmG.minLoc, 2, new Scalar(255, 255, 255, 255), 2);
            iris.x = mmG.minLoc.x + eye_only_rectangle.x;
            iris.y = mmG.minLoc.y + eye_only_rectangle.y;
            eye_template = new Rect((int) iris.x - size / 2, (int) iris.y
                    - size / 2, size, size);
            Core.rectangle(mRgba, eye_template.tl(), eye_template.br(),
                    new Scalar(255, 0, 0, 255), 2);
            template = (mGray.submat(eye_template)).clone();
            return template;
        }
        return template;
    }

    private void  mouthAnalysis(int distance){
        double prob_of_open;
        mouth_alert=0;
        mouth_count++;
        if(distance<65){
            open_mouth++;

        }
        if(distance>52){
            yawn_count++;
            mouth_yawn++;

        }
        if(mouth_count <= 20){
            if(mouth_yawn > 5) {
                mouth_count = 0;
                mouth_yawn = 0;
                mouth_alert = 1;
            }

            if(mouth_count == 20){
                mouth_count = 0;
            }
            drowsinessDetection();            //call drowsiness function
        }

        prob_of_opening=(float)open_mouth/mouth_detect;
        prob_of_yawn=(float)yawn_count/mouth_detect;
        System.out.println("Yawning Probablityyyyyyyy" + prob_of_yawn);


        if(mouth_detect == 20){
            mouth_detect = 0;
            yawn_count = 0;
            open_mouth = 0;
            mouth_warning = 0;
        }




    }

    private void match_eye(Rect area, Mat mTemplate, int type) {
        Point matchLoc;
        Mat mROI = mGray.submat(area);
        int result_cols = mROI.cols() - mTemplate.cols() + 1;
        int result_rows = mROI.rows() - mTemplate.rows() + 1;
        // Check for bad template size
        if (mTemplate.cols() == 0 || mTemplate.rows() == 0) {
            return;
        }
        Mat mResult = new Mat(result_cols, result_rows, CvType.CV_8U);

        switch (type) {
            case TM_SQDIFF:
                Imgproc.matchTemplate(mROI, mTemplate, mResult, Imgproc.TM_SQDIFF);
                break;
            case TM_SQDIFF_NORMED:
                Imgproc.matchTemplate(mROI, mTemplate, mResult,
                        Imgproc.TM_SQDIFF_NORMED);
                break;
            case TM_CCOEFF:
                Imgproc.matchTemplate(mROI, mTemplate, mResult, Imgproc.TM_CCOEFF);
                break;
            case TM_CCOEFF_NORMED:
                Imgproc.matchTemplate(mROI, mTemplate, mResult,
                        Imgproc.TM_CCOEFF_NORMED);
                break;
            case TM_CCORR:
                Imgproc.matchTemplate(mROI, mTemplate, mResult, Imgproc.TM_CCORR);
                break;
            case TM_CCORR_NORMED:
                Imgproc.matchTemplate(mROI, mTemplate, mResult,
                        Imgproc.TM_CCORR_NORMED);
                break;
        }

        Core.MinMaxLocResult mmres = Core.minMaxLoc(mResult);
        // there is difference in matching methods - best match is max/min value
        if (type == TM_SQDIFF || type == TM_SQDIFF_NORMED) {
            matchLoc = mmres.minLoc;
        } else {
            matchLoc = mmres.maxLoc;
        }

        Point matchLoc_tx = new Point(matchLoc.x + area.x, matchLoc.y + area.y);
        Point matchLoc_ty = new Point(matchLoc.x + mTemplate.cols() + area.x,
                matchLoc.y + mTemplate.rows() + area.y);

        Core.rectangle(mRgba, matchLoc_tx, matchLoc_ty, new Scalar(255, 255, 0,
                255));
        Rect rec = new Rect(matchLoc_tx, matchLoc_ty);


    }

    private void   eyeMonitoring(Mat mRgba, Mat mGray) {
        MatOfRect faceDetections = new MatOfRect();

        eyeDetections = new MatOfRect();

        if (mJavaDetector != null)
            mJavaDetector.detectMultiScale(mGray, faceDetections, 1.1, 2, 2, // TODO: objdetect.CV_HAAR_SCALE_IMAGE
                    new Size(mAbsoluteFaceSize, mAbsoluteFaceSize), new Size());


        for (int i = 0; i < faceDetections.toArray().length; i++) {
            System.out.println(" jjjjjjjjjjjjjjjjj");
            face = mRgba.submat(faceDetections.toArray()[i]);
            crop = face.submat(4, (2 * face.width()) / 3, 0, face.height());
            bitmap1 = Bitmap.createBitmap(face.width(), face.height(), Bitmap.Config.ARGB_8888);
            //Utils.matToBitmap(face, bitmap1);
            //img1.setImageBitmap(bitmap1);
            bitmap2 = Bitmap.createBitmap(crop.width(), crop.height(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(crop, bitmap2);
            img2.setImageBitmap(bitmap2);

            if (mJavaDetectorEye != null)
                mJavaDetectorEye.detectMultiScale(crop, eyeDetections, 1.1, 2, 0, new Size(4, 4), new Size());
            if (eyeDetections.toArray().length == 0) {
                System.out.println(" Not a face" + i);
            } else {
                System.out.println("Face with " + eyeDetections.toArray().length + "eyes");
                for (int j = 0; j < eyeDetections.toArray().length; j++) {

                    System.out.println("Eye");
                    eye = crop.submat(eyeDetections.toArray()[j]);
                    System.out.println("Widthhhhhhhhhhhh... " + eye.width() + "," + eye.height());
                    if (eyeDetections.toArray()[j].width > 0) {
                        bitmap3 = Bitmap.createBitmap(eye.cols(), eye.rows(), Bitmap.Config.RGB_565);
                        Utils.matToBitmap(eye, bitmap3);
                        img3.setImageBitmap(bitmap3);
                        long startTime = System.currentTimeMillis();
                        new MonitoringEye().execute();
                        long endTime = System.currentTimeMillis();
                        long Time=endTime-startTime;
                        System.out.println("Timeeeeee....."+Time);

                    } else {
                        System.out.print("Not acceptable!!");
                        break;
                    }
                }
            }
        }
    }


    private class MonitoringEye extends AsyncTask<String, Integer, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... arg) {

            mRgba1 = new Mat();
            mGray1 = new Mat();

            final int w = bitmap3.getWidth();
            final int h = bitmap3.getHeight();


            int A, R, G, B;
            int pixelColor;

            inverted = Bitmap.createBitmap(eye.cols(), eye.rows(), Bitmap.Config.RGB_565);
            // pixelColor = bitmap3.getPixel(w-2, h-2);


            for (int y = 0; y < inverted.getHeight() - 2; y++) {
                for (int x = 0; x < inverted.getWidth() - 2; x++) {

                    if (x < bitmap3.getWidth() && y < bitmap3.getHeight()) {


                        pixelColor = bitmap3.getPixel(x, y);
                        A = Color.alpha(pixelColor);
                        R = 255 - Color.red(pixelColor);
                        G = 255 - Color.green(pixelColor);
                        B = 255 - Color.blue(pixelColor);
                        // set newly-inverted pixel to output image
                        inverted.setPixel(x, y, Color.argb(A, R, G, B));
                    }
                }

            }

            // img2.setImageBitmap(inverted);
            Utils.bitmapToMat(inverted, mRgba1);
            Imgproc.cvtColor(mRgba1, mGray1, Imgproc.COLOR_RGB2GRAY);
            imagegray = Bitmap.createBitmap(mGray1.width(), mGray1.height(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(mGray1, imagegray);
            // img3.setImageBitmap(imagegray);           // gray scale image
            int alpha, p;
            int newPixel;
            int b = 0;
            int threshold = 220;
            open_eye=0;
            binarized = Bitmap.createBitmap(imagegray.getWidth(), imagegray.getHeight(), Bitmap.Config.ARGB_8888);

            for (int i = 0; i < imagegray.getWidth(); i++) {
                for (int j = 0; j < imagegray.getHeight(); j++) {

                    p = imagegray.getPixel(i, j);
                    int red = (int) (Color.red(p));
                    alpha = (int) (Color.alpha(p));
                    System.out.println("Reddddddddddddddddddddddddddd..................: " + red);
                    if (red > 212) {//238


                        open_eye++;
                        newPixel = 255;

                    } else {
                        newPixel = 0;


                    }

                    int newPixelb = 0;
                    newPixelb += alpha;
                    newPixelb = newPixelb << 8;
                    newPixelb += newPixel;
                    newPixelb = newPixelb << 8;
                    newPixelb += newPixel;
                    newPixelb = newPixelb << 8;
                    newPixelb += newPixel;
                    // newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                    binarized.setPixel(i, j, newPixelb);
                }
            }
            setOpen_eye(open_eye);

            return binarized;
        }

        @Override
        protected void onPostExecute(Bitmap img) {


            if (img4 != null && img != null) {

                img4.setImageBitmap(img);
            }

        }
    }

    private void setOpen_eye(int value){
        System.out.println("valueeeeeee.. "+value);
        if(value>10){
            setvalues(1);

        }
        else{
            setvalues(0);

        }

    }
    private void setvalues(int value  ){

        eye_array[counte]=value;
        System.out.println("Eye Arrrayyyyyyyyyyyyyy..................: " +eye_array[counte]+"   " + counte);
        counte++;
        if(counte==30){
            counte=0;
        }
        eyeAnalysis(value);
    }

    private void eyeAnalysis(int value){

        eyeCount++;
        eye_detect++;
        if(value==0){
            eye_close++;
            eye_close_frame++;

        }

        if(value==1){
            eye_open++;
          // eye_open_frame++;

        }

         if(eye_detect <= 20){
            if(eye_close_frame > 14) {
                eye_close_frame = 0;
                eye_detect = 0;
                eye_alert = 1;
            }

            if(eye_detect == 20){
                eye_detect = 0;
            }
            }

        prob_of_close_eyes=(double)eye_close/ eyeCount;
        prob_of_open_eyes=(double)eye_open/eyeCount;
        if(eyeCount == 20){
            eyeCount = 0;
            eye_close = 0;
            eye_open = 0;
           eye_warning = 0;

        }
        System.out.println("Eye Probability Value" +value);
        System.out.println("Eye Probability" +prob_of_close_eyes);

        //prev_eye=mean;
        //text=text+value;
        //mean= (float)text/eyeCount;
        //System.out.println("Eyee Opennnnnnnnnnnnn..."+ value);

        //System.out.println("meannnnnnnnnnn"+mean+ "frames"+learn_frames);
        drowsinessDetection();
    }
    private void headAnalysis(double head_angle){
        head_frame++;
        head_alert = 0;
        if(head_angle > 280.0 || head_angle <260.0){
            head_wrong ++;
            wrong_frame++;
        }
        if(head_angle > 260.0 && head_angle < 280.0){
            head_correct++;
        }
        prob_head_correct = (double)head_correct/head_detect;
        prob_head_wrong = (double)head_wrong/head_detect;

        if(head_frame <= 20){
            if(wrong_frame > 10) {
                wrong_frame = 0;
                head_frame = 0;
                head_alert = 1;
            }

            if(head_frame == 20){
                head_frame = 0;
            }

            drowsinessDetection();           //call drowsiness function
        }


        if(head_detect == 20){
            head_detect = 0;
            head_wrong = 0;
            head_correct = 0;
            head_warning = 0;

        }





    }
    private void CreateAuxiliaryMats() {
        if (mGray.empty())
            return;

        int rows = mGray.rows();
        int cols = mGray.cols();

        if (mZoomWindow == null) {
            mZoomWindow = mRgba.submat(rows / 2 + rows / 10, rows, cols / 2
                    + cols / 10, cols);
            mZoomWindow2 = mRgba.submat(0, rows / 2 - rows / 10, cols / 2
                    + cols / 10, cols);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "called onCreateOptionsMenu");
        mItemSwitchCamera = menu.add("Toggle Native/Java camera");
        mItemFace50 = menu.add("Face size 50%");
        mItemFace40 = menu.add("Face size 40%");
        mItemFace30 = menu.add("Face size 30%");
        mItemFace20 = menu.add("Face size 20%");
        mItemType = menu.add(mDetectorName[mDetectorType]);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String toastMesage = new String();
        Log.i(TAG, "called onOptionsItemSelected; selected item: " + item);
        if (item == mItemFace50)
            setMinFaceSize(0.5f);
        else if (item == mItemFace40)
            setMinFaceSize(0.4f);
        else if (item == mItemFace30)
            setMinFaceSize(0.3f);
        else if (item == mItemFace20)
            setMinFaceSize(0.2f);
        else if (item == mItemType) {
            int tmpDetectorType = (mDetectorType + 1) % mDetectorName.length;
            item.setTitle(mDetectorName[tmpDetectorType]);
        }
        return true;


    }

    private void setMinFaceSize(float faceSize) {
        mRelativeFaceSize = faceSize;
        mAbsoluteFaceSize = 0;
    }

    private class headMonitoring extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... arg) {


            //ImageView imageView = (ImageView) findViewById(R.id.imageView1);

            FaceDetector.Face[] faceme = new FaceDetector.Face[3];
            FaceDetector faceDet = new FaceDetector(image.getWidth(), image.getHeight(), MAX_FACES);
            //Face[] faceList = new Face[MAX_FACES];
            int number1 = faceDet.findFaces(image, faceme);
            System.out.println("Number" + number1);
            Log.d("reached", "reached");
            System.out.println("length" + faceme.length);
            for (int i = 0; i < faceme.length; i++) {
                Face face = faceme[i];
                System.out.println("In for loop");
                if (face != null) {
                    head_detect ++;
                    System.out.println("In if ");
                    //Log.d("FaceDet1", "In if loop");
                    PointF pf = new PointF();
                    face.getMidPoint(pf);
                    double x, y;
                    x = (double) pf.x;
                    y = (double) pf.y;
                    System.out.println("imagewidth" + x);
                    System.out.println("imageheight" + y);
                    System.out.println("point" + pf);
                    double w = (double) image.getWidth() / 2;
                    double h = (double) image.getHeight() / 2;
                    System.out.println("centrewidth" + w);
                    System.out.println("centreheight" + h);

                    double diffx = w - x;
                    double diffy = h - y;

                    double Length = Math.sqrt(diffx + diffy);
                    double value = (x - w) / Length;
                    angle = Math.acos(value) * (180 / Math.PI);
                    ;
                    if (h > y) {
                        angle = 360 - angle;
                    }
                    headAnalysis(angle);
                    //if((x > w)) {//above 0 to 180 degrees


                    //angle = (float) (Math.atan2((y - h),(x - w) ) * (180 / Math.PI));

                    //}
                    //else if((x < w)) {//above 180 degrees to 360/0

                    //angle = (float) (360 - (Math.atan2((h - y),(w - x) ) * (180 / Math.PI)));

                    //}//End if((secondPoint.x > firstPoint.x) && (secondPoint.y <= firstPoint.y))

                    System.out.println("angle" + angle);
                    String total2 = String.valueOf(angle);
                    if (angle < 260.0 || angle > 280.0) {
                        System.out.println("Head is Tilted!!");
                        Log.d("TAG", total2);
                    }
                    headAngle(angle);
                    // System.out.println("yyyyyyyyyyyyyyyy" +angle_array[counter-1]);
                }
            }

            return  null;
        }
    }

    public void headAngle(double angle){
        if (counter == 30) {
            counter = 0;
        }
        angle_array[counter] = angle;
        counter++;
    }
    public void drowsinessDetection() {
        mouth_factor = prob_of_yawn * 0.25;
        head_factor = prob_head_wrong * 0.15;
        eye_factor = prob_of_close_eyes * 0.60;
        drowsy_measure = mouth_factor + head_factor + eye_factor;
        drowsyMeasure(drowsy_measure);
        System.out.println("Factor Yawn" + mouth_factor);
        System.out.println("Head angle" + angle);
        System.out.println("head Probability" + prob_head_wrong);
        System.out.println("Factor Head" + head_factor);
        System.out.println("Eyes Probability" + prob_of_close_eyes);
        System.out.println("eye factor's" + eye_factor);
        System.out.println("Factor drowsy" + drowsy_measure);
        if (mouth_factor >= 0.56) {
            mouth_warning = 1;
        }
        if (head_factor >= 0.0383) {
            head_warning = 1;
        }
        if (eye_factor > 0) {
            eye_warning = 1;
        }
        head_weightage = head_warning;
        mouth_weightage = mouth_warning;
        eye_weightage = eye_warning ;
        if (eye_weightage == 0 && mouth_weightage == 0 && head_weightage == 0) {
            normal_zone = 1;
        } else if (eye_weightage == 0 && mouth_weightage == 0 && head_weightage == 1) {
            normal_zone = 1;
        } else if (eye_weightage == 0 && mouth_weightage == 0 && head_weightage == 2) {
            normal_zone = 1;
        } else if (eye_weightage == 0 && mouth_weightage == 1 && head_weightage == 0) {
            normal_zone = 1;
        } else if (eye_weightage == 0 && mouth_weightage == 1 && head_weightage == 1) {
            normal_zone = 1;
        } else if (eye_weightage == 0 && mouth_weightage == 1 && head_weightage == 2) {
            normal_zone = 1;
        } else if (eye_weightage == 0 && mouth_weightage == 2 && head_weightage == 0) {
            warning_zone = 1;
        } else if (eye_weightage == 0 && mouth_weightage == 2 && head_weightage == 1) {
            warning_zone = 1;
        }else if (eye_weightage == 1 && mouth_weightage == 2 && head_weightage == 1) {
            danger_zone = 1;
        } else if (eye_weightage == 1 && mouth_weightage == 2 && head_weightage == 2) {
            danger_zone = 1;
        } else if (eye_weightage == 2 && mouth_weightage == 1 && head_weightage == 2) {
            danger_zone = 1;
        } else if (eye_weightage == 2 && mouth_weightage == 2 && head_weightage == 0) {
            danger_zone = 1;
        } else if (eye_weightage == 2 && mouth_weightage == 2 && head_weightage == 1) {
            danger_zone = 1;
        } else if (eye_weightage == 2 && mouth_weightage == 2 && head_weightage == 2) {
            danger_zone = 1;
        }
        System.out.println("Weightage Eye" + eye_weightage);
        System.out.println("Weightage Mouth" + mouth_weightage);
        System.out.println("Weightage Head" + head_weightage);



            if (danger_zone == 1 && normal_zone == 0) {


                showNotification();
                showNotification();
                System.out.println("Weightage danger" + danger_zone);
                danger_zone = 0;

            }

        if (warning_zone == 1 && normal_zone == 0) {
            showNotification();
            System.out.println("Weightage warning" + warning_zone);

        }
        drowsy_frame = 0;

        warning_zone=0;
        danger_zone = 0;
        normal_zone = 0;
        drowsy_frame++;

    }


    public void drowsyMeasure(double measure){
        if (countd == 30) {
            countd = 0;
        }

        System.out.println("Factor drowsy measure"+measure);
        drowsy_array[countd] = measure;
        countd++;
    }

    public void showNotification(){

        // define sound URI, the sound to be played when there's a notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // intent triggered, you can add other intent for other actions

        // this is it, we'll build the notification!
        // in the addAction method, if you don't want any icon, just set the first param to 0
        Notification mNotification = new Notification.Builder(this)


                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, mNotification);
    }

}

