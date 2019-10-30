import java.awt.*;

/**
 * Created by respe on 5/8/2017.
 */
public class Blob {
    public int x;
    public int y;

    public int width=20;
    public int height =20;
    public Rectangle rectBot;

    public Blob()
    {
        x=(int)(Math.random()*490);
        y=(int)(Math.random()*575);
        rectBot = new Rectangle(x,y,width,height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
