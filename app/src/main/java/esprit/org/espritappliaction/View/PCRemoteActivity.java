package esprit.org.espritappliaction.View;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.CheckBox;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import esprit.org.espritappliaction.R;

public class PCRemoteActivity extends AppCompatActivity implements View.OnClickListener,SensorEventListener {
    private Socket client;
    private Boolean flag = false;
    TextView pcIp,mousePad;
    private boolean mouseMoved=false;
    private  SensorManager mSensorManager;
    private  Sensor mDevicePostion;

    ScrollView scrollView,ScrollMouse;
    boolean isScrollable=true;
    String message,ip;
    ButtonFloat play, stop, volume_up, volume_down, next, previous, playPPT, stopPPT, nextPPT, previousPPT,configs;
CheckBox checkMouse;
    ButtonRectangle left,right;
String GMS;
    private float initX =0;
    private float initY =0;
    private float disX =0;
    private float disY =0;
    public boolean mouse=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcremote);

     /* // Pushbots.sharedInstance().init(this);
       // Pushbots.sharedInstance().setCustomHandler(CustomNotifcationAdapter.class);
        List<String> tags=new ArrayList<String>();
         tags.add("clubs");
     //   tags.add("news");

        Payload payload=new Payload("http://espritmobile.com/wp-content/uploads/2014/01/LOGO-ESPRIT-MOBILE.png","un titrte personalise","com.esprit.smartesprit.Activity.AuthentificationActivity");

        SendPushToAllService sendPushToAllService=new SendPushToAllService(new Notification("c'est un test :)",tags,payload));
    // sendPushToAllService.execute();
        GCMRegistrar.register(this, "...");
       // GMS=getRegistrationId(this);
        GMS=GCMRegistrar.getRegistrationId(this);
       TagDeviceService tagDeviceService=new TagDeviceService(new Tag(GMS,"clubs"));
       tagDeviceService.execute();
//

        sendPushToAllService.execute();

        System.out.println("GMS = "+ GMS);
        System.out.println("sender id "+       Pushbots.sharedInstance().getSenderId()
        );
        */

        pcIp=(TextView)findViewById(R.id.pcIp);
        ip= pcIp.getText().toString();
        play = (ButtonFloat) findViewById(R.id.play);
        play.setOnClickListener(this);
        stop = (ButtonFloat) findViewById(R.id.stop);
        stop.setOnClickListener(this);
        volume_up = (ButtonFloat) findViewById(R.id.volume_up);
        volume_up.setOnClickListener(this);
        volume_down = (ButtonFloat) findViewById(R.id.volume_down);
        volume_down.setOnClickListener(this);

        next = (ButtonFloat) findViewById(R.id.next);
        next.setOnClickListener(this);
        previous = (ButtonFloat) findViewById(R.id.previous);
        previous.setOnClickListener(this);

        playPPT = (ButtonFloat) findViewById(R.id.playPPT);
        playPPT.setOnClickListener(this);
        stopPPT = (ButtonFloat) findViewById(R.id.stopPPT);
        stopPPT.setOnClickListener(this);
        nextPPT = (ButtonFloat) findViewById(R.id.nextPPT);
        nextPPT.setOnClickListener(this);
        previousPPT = (ButtonFloat) findViewById(R.id.previousPPT);
        previousPPT.setOnClickListener(this);

scrollView=(ScrollView)findViewById(R.id.scollRemote);
       // ScrollMouse=(ScrollView)findViewById(R.id.scrollMouse);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mDevicePostion = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

      scrollView.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              if (!checkMouse.isCheck()) {
                  return false;
              } else {

                  return true;
              }
          }
      });
left=(ButtonRectangle)findViewById(R.id.leftclick);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    message="left_click";
                    SendMessage sendMessageTask = new SendMessage();
                    sendMessageTask.execute();


            }
        });
        right=(ButtonRectangle)findViewById(R.id.rightclick);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    message="right_click";
                    SendMessage sendMessageTask = new SendMessage();
                    sendMessageTask.execute();


            }
        });
mousePad=(TextView)findViewById(R.id.mousePad);

        mousePad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            //save X and Y positions when user touches the TextView
                            initX =event.getX();
                            initY =event.getY();
                            mouseMoved=false;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            disX = event.getX()- initX; //Mouse movement in x direction
                            disY = event.getY()- initY; //Mouse movement in y direction
                            /*set init to new position so that continuous mouse movement
                            is captured*/
                            initX = event.getX();
                            initY = event.getY();
                            if((disX !=0 && disY !=0)&&(mouse==true)){
                                message=disX +","+ disY; //send mouse movement to server
                                mouseMoved=true;
                            }
                            else if(!mouse)
                            {
                                message="";
                            }

                            break;
                        case MotionEvent.ACTION_UP:
                            //consider a tap only if usr did not move mouse after ACTION_DOWN
                            if(!mouseMoved){
                                message="left_click";
                            }
                    }
                SendMessage sendMessageTask = new SendMessage();
                sendMessageTask.execute();

                return true;
            }
        });




checkMouse=(CheckBox)findViewById(R.id.checkMouse);
        checkMouse.setOncheckListener(new CheckBox.OnCheckListener() {
            @Override
            public void onCheck(CheckBox view, boolean check) {

                if (view.isCheck()) {


                   //onResume();
                    mouse=true;
                    Toast.makeText(getApplicationContext(), "Mouse Enable", Toast.LENGTH_LONG).show();

                } else {
                   // findViewById(R.id.RemoteContenu).setVisibility(View.VISIBLE);
                  //  scrollView.setVisibility(View.VISIBLE);
//onPause();
                    mouse=false;
                     Toast.makeText(getApplicationContext(), "Mouse disable ", Toast.LENGTH_LONG).show();

                }
            }
        });


    }


    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mDevicePostion, SensorManager.SENSOR_DELAY_NORMAL);
        message=mSensorManager.AXIS_X +","+ mSensorManager.AXIS_MINUS_Y;

     //   System.out.println("message sensor = " +message);
        SendMessage sendMessageTask = new SendMessage();
        sendMessageTask.execute();
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                message = "play";
                break;
            case R.id.stop:
                message="stop";
                break;
            case  R.id.volume_up:
                message="volume_up";
                break;
            case  R.id.volume_down:
                message="volume_down";
                break;
            case  R.id.next:
                message="next";
                break;
            case  R.id.previous:
                message="previous";
                break;
            case  R.id.playPPT:
                message="playPPT";
                break;
            case  R.id.stopPPT:
                message="stopPPT";
                break;
            case  R.id.nextPPT:
                message="nextPPT";
                break;
            case  R.id.previousPPT:
                message="previousPPT";
                break;
          /*  case R.id.leftclick:
                message="left_click";
                break;
            case R.id.rightclick:
                message="right_click";
                break;
*/

        }
        if(flag==true)	{
            Toast.makeText(PCRemoteActivity.this, "Connection failed or Invalid IpAddress !!", Toast.LENGTH_LONG).show();
            // msg.setText("Could not Connected or Invalid IpAddress !!");
            flag = false;



        }
        ip= pcIp.getText().toString();
        System.out.println("my ip "+ip);
        System.out.println(" message "+message);
        SendMessage sendMessageTask = new SendMessage();
        sendMessageTask.execute();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        message=event.values[0] +","+ event.values[1];

        System.out.println("message sensor = " +message);
        SendMessage sendMessageTask = new SendMessage();
        sendMessageTask.execute();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private class SendMessage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //String str = new String("116.202.91.218");
                client = new Socket(ip, 9102); // connect to the server


                PrintWriter outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                outStream.println(message);
                outStream.close();


            } catch (Exception e) {
                flag=true;
            }
            return null;
        }

    }

}