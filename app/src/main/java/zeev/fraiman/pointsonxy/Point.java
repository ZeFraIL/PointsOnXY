package zeev.fraiman.pointsonxy;

public class Point {
    private String name;
    private int x;
    private int y;

    public Point(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
