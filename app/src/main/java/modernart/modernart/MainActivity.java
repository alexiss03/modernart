package modernart.modernart;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.animation.ArgbEvaluator;

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

        cells = new ArrayList<LinearLayout>();
        destinationColors = new ArrayList<Integer>();
        sourceColors = new ArrayList<Integer>();

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

        for (int index = 0;  index < cells.size(); index++) {
            Random rnd =  new Random();
            int randomIfCellIsNonWhiteOrNonGrey = rnd.nextInt(10);

            if (randomIfCellIsNonWhiteOrNonGrey < 1) {
                Integer whiteColor = Color.argb(255, 255, 255, 255);
                sourceColors.add(whiteColor);
                cells.get(index).setBackgroundColor(whiteColor);
            } else if (randomIfCellIsNonWhiteOrNonGrey < 2) {
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

}
