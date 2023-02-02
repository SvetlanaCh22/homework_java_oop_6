public class Vector2 {
    int x,y;

    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getDistance(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public int getDistanceInSteps(int x, int y) {
        int dx = Math.abs(x - this.x);
        int dy = Math.abs(y - this.y);
        return dx + dy - 1;
    }

    public boolean isEquals(Vector2 opposit){
        return opposit.y == y && opposit.x == x;
    }
}
