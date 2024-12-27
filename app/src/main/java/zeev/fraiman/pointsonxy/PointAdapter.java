package zeev.fraiman.pointsonxy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class PointAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Point> points;

    public PointAdapter(Context context, ArrayList<Point> points) {
        this.context = context;
        this.points = points;
    }

    @Override
    public int getCount() {
        return points.size();
    }

    @Override
    public Object getItem(int position) {
        return points.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.point_list_item, parent, false);
        }

        Point point = points.get(position);
        TextView pointName = convertView.findViewById(R.id.point_name);
        TextView pointCoords = convertView.findViewById(R.id.point_coords);

        pointName.setText(point.getName());
        pointCoords.setText("(" + point.getX() + ", " + point.getY() + ")");

        return convertView;
    }
}
