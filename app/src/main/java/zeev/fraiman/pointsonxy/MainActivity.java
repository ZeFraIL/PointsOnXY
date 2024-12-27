package zeev.fraiman.pointsonxy;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CoordinateView coordinateView;
    private ListView pointListView;
    private PointAdapter pointAdapter;
    private ArrayList<Point> pointList;
    private EditText inputX, inputY;
    private Switch inputSwitch;
    private Button addPointButton, clearButton, saveButton, readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinateView = findViewById(R.id.coordinate_view);
        pointListView = findViewById(R.id.point_list);
        inputX = findViewById(R.id.input_x);
        inputY = findViewById(R.id.input_y);
        inputSwitch = findViewById(R.id.input_switch);
        addPointButton = findViewById(R.id.add_point_button);
        clearButton = findViewById(R.id.clear_button);
        saveButton = findViewById(R.id.save_button);
        readButton = findViewById(R.id.read_button);

        pointList = new ArrayList<>();
        pointAdapter = new PointAdapter(this, pointList);
        pointListView.setAdapter(pointAdapter);

        inputSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputSwitch.setText("Ввод нажатием");
                    inputX.setEnabled(false);
                    inputY.setEnabled(false);
                    addPointButton.setEnabled(false);
                } else {
                    inputSwitch.setText("Ввод числами");
                    inputX.setEnabled(true);
                    inputY.setEnabled(true);
                    addPointButton.setEnabled(true);
                }
            }
        });

        addPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputSwitch.isChecked()) {
                    int x = Integer.parseInt(inputX.getText().toString());
                    int y = Integer.parseInt(inputY.getText().toString());
                    addPoint(x, y);
                }
            }
        });

        coordinateView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (inputSwitch.isChecked()) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        float touchX = event.getX();
                        float touchY = event.getY();
                        int x = (int) ((touchX - coordinateView.getWidth() / 2) / 10);
                        int y = (int) ((coordinateView.getHeight() / 2 - touchY) / 10);
                        addPoint(x, y);
                    }
                    return true;
                }
                return false;
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointList.clear();
                pointAdapter.notifyDataSetChanged();
                coordinateView.clearPoints();
            }
        });

        // The functions for the database (save and read)
        // can be implemented by the programmer later,
        // at his own discretion.
    }

    private void addPoint(int x, int y) {
        String pointName = getNextPointName();
        Point newPoint = new Point(pointName, x, y);
        pointList.add(newPoint);
        pointAdapter.notifyDataSetChanged();
        coordinateView.addPoint(newPoint);
    }

    private String getNextPointName() {
        int size = pointList.size();
        return String.valueOf((char) ('A' + size));
    }
}
