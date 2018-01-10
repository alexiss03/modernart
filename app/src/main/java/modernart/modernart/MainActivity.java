package modernart.modernart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.animation.ArgbEvaluator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;


import java.util.*;

public class MainActivity extends AppCompatActivity {

    private List<LinearLayout> cells;

    private List<Integer> destinationColors;
    private List<Integer> sourceColors;

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCells();
        setColors();
        setSeekBar();
    }

    private void setCells() {
        cells = new ArrayList<LinearLayout>();
        cells.add((LinearLayout) findViewById(R.id.cell1));
        cells.add((LinearLayout) findViewById(R.id.cell2));
        cells.add((LinearLayout) findViewById(R.id.cell3));
        cells.add((LinearLayout) findViewById(R.id.cell4));
        cells.add((LinearLayout) findViewById(R.id.cell5));
        cells.add((LinearLayout) findViewById(R.id.cell6));
        cells.add((LinearLayout) findViewById(R.id.cell7));
        cells.add((LinearLayout) findViewById(R.id.cell8));
        cells.add((LinearLayout) findViewById(R.id.cell9));
        cells.add((LinearLayout) findViewById(R.id.cell10));
    }

    private void setColors() {
        destinationColors = new ArrayList<Integer>();
        sourceColors = new ArrayList<Integer>();

        Random rnd =  new Random();
        Integer whiteCellIndex = rnd.nextInt(cells.size());
        Integer greyCellIndex = rnd.nextInt(cells.size());

        for (int index = 0;  index < cells.size(); index++) {
            if (whiteCellIndex == index) {
                Integer whiteColor = Color.argb(255, 255, 255, 255);
                sourceColors.add(whiteColor);
                cells.get(index).setBackgroundColor(whiteColor);
            } else if (greyCellIndex == index) {
                Integer whiteColor = Color.argb(255, 230, 230, 230);
                sourceColors.add(whiteColor);
                cells.get(index).setBackgroundColor(whiteColor);
            } else {
                Integer sourceColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                sourceColors.add(sourceColor);
                cells.get(index).setBackgroundColor(sourceColor);
            }

            Integer destinationColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            destinationColors.add(destinationColor);
        }
    }

    private void setSeekBar() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               for (int index = 0;  index < cells.size(); index++) {
                   LinearLayout cell = cells.get(index);
                   Integer destinationColor = destinationColors.get(index);
                   Integer sourceColor = sourceColors.get(index);

                   if (sourceColor == Color.argb(255, 255, 255, 255) ||
                           sourceColor ==  Color.argb(255, 230, 230, 230)) {
                       continue;
                   }

                   Integer destination = (Integer) new ArgbEvaluator().evaluate(0.01f * i, sourceColor, destinationColor);
                   cell.setBackgroundColor(destination);
               }
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       }

        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        showMoreInformationDialog();
        return super.onOptionsItemSelected(item);
    }

    private void showMoreInformationDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson.\n\nClick below to know more.");
        dialog.setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                startActivity(browserIntent);
            }
        })
                .setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        dialog.show();
    }
}
