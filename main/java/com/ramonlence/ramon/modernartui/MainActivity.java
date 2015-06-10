package com.ramonlence.ramon.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    private SeekBar seekBar;
    private LinearLayout col1;
    private LinearLayout col2;
    private LinearLayout col3;
    private FrameLayout row1;
    private FrameLayout row2;
    private FrameLayout row3;
    private FrameLayout row4;
    private FrameLayout row5;
    private LinearLayout.LayoutParams col1_params;
    private LinearLayout.LayoutParams col2_params;
    private LinearLayout.LayoutParams col3_params;
    private LinearLayout.LayoutParams row1_params;
    private LinearLayout.LayoutParams row2_params;
    private float col1_weight;
    private float col2_weight;
    private float col3_weight;
    private float row1_weight;
    private float row2_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLayoutElements();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update_columns_weight(progress);
                update_rows_weight(progress);
                update_colors(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setupLayoutElements() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        col1 = (LinearLayout) findViewById(R.id.column1);
        col1_params = (LinearLayout.LayoutParams) col1.getLayoutParams();
        col1_weight = col1_params.weight;
        col2 = (LinearLayout) findViewById(R.id.column2);
        col2_params = (LinearLayout.LayoutParams) col2.getLayoutParams();
        col2_weight = col2_params.weight;
        col3 = (LinearLayout) findViewById(R.id.column3);
        col3_params = (LinearLayout.LayoutParams) col3.getLayoutParams();
        col3_weight = col3_params.weight;
        row1 = (FrameLayout) findViewById(R.id.row1);
        row1_params = (LinearLayout.LayoutParams) row1.getLayoutParams();
        row1_weight = row1_params.weight;
        row2 = (FrameLayout) findViewById(R.id.row2);
        row2_params = (LinearLayout.LayoutParams) row2.getLayoutParams();
        row2_weight = row2_params.weight;
        row3 = (FrameLayout) findViewById(R.id.row3);
        row4 = (FrameLayout) findViewById(R.id.row4);
        row5 = (FrameLayout) findViewById(R.id.row5);
    }

    private void update_columns_weight(int progress){
        col1_params.weight = col1_weight + 0.1f * progress;
        col2_params.weight = col2_weight + 0.2f * progress;
        col3_params.weight = col3_weight + 0.05f * progress;
        col1.setLayoutParams(col1_params);
        col2.setLayoutParams(col2_params);
        col3.setLayoutParams(col3_params);
    }

    private void update_rows_weight(int progress){
        row1_params.weight = row1_weight - (progress / 25f);
        row1.setLayoutParams(row1_params);
        row2_params.weight = row2_weight + (progress / 50f);
        row2.setLayoutParams(row2_params);
    }

    private void update_colors(int progress) {
        row3.setBackgroundColor(calcColor(Color.parseColor("#FFFF9E9D"), Color.parseColor("#FF0C9DA9"), progress));
        row4.setBackgroundColor(calcColor(Color.parseColor("#FF7FC7AF"), Color.parseColor("#FFFBC38D"), progress));
    }

    private static int ave(int src, int dst, float p) {
        return src + java.lang.Math.round(p * (dst - src));
    }

    private int calcColor(int a, int b, int progress) {
        float proportion = progress / 100f;
        int alpha = ave(Color.alpha(a), Color.alpha(b), progress/100f);
        int red = ave(Color.red(a), Color.red(b), progress/100f);
        int green = ave(Color.green(a), Color.green(b), progress/100f);
        int blue = ave(Color.blue(a), Color.blue(b), progress/100f);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("MORE INFORMATION")
                    .setMessage("Click on the link to visit the art store")
                    .setCancelable(false)
                    .setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String url = "http://www.bygart.com";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    })
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
