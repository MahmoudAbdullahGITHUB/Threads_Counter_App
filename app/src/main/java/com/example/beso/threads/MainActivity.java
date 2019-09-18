package com.example.beso.threads;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Activity myActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActivity  =this;
    }

    myFirstThread myFirstThread;
    mySecondThread myFirstThread2;
    static boolean runner =true;

    public void BuStart(View view) {
        myFirstThread = new myFirstThread();
        myFirstThread.start();
        myFirstThread2 = new mySecondThread();
        myFirstThread2.start();
    }

    public void BuStop(View view) {
        runner = false;
    }



    public void BuMessage(View view) {
        Toast.makeText(this, "message on the main thread", Toast.LENGTH_SHORT).show();
    }

    class myFirstThread extends Thread{
        int x=0;

        public void run() {

            final TextView textView = (TextView) findViewById(R.id.thread1);
            while (runner){

                    myActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(x));
                        }
                    });

                x++;
                try {
                    Thread.sleep(1000);
                }catch (Exception ex){

                }
            }

        }
    }

    class mySecondThread extends Thread{
        int x=0;
        final TextView textView = (TextView) findViewById(R.id.thread2);
        public void run() {

            while (runner){

                myActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(x));
                        }
                    });

                x++;
                try {
                    Thread.sleep(2000);
                }catch (Exception ex){

                }
            }

        }
    }

}
