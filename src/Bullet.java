import com.sun.javafx.image.BytePixelSetter;

import java.awt.*;

/**
 * Created by respe on 5/5/2017.
 */
public class Bullet {
    int x;
    int y;
    int width;
    int height;
    int direction;
    int firex;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;


    public Rectangle rectBot;
    public Bullet(int x,int y)
    {
        this.x=x;
        this.y=y;
        x-=5;
        width=10;
        height =20;
        rectBot = new Rectangle(x,y,width,height);
    }
    public Bullet(int x,int y, int width, int height,int direction)
    {
        this.x=x;
        this.y=y;
        x-=5;
        this.width = width;
        this.height =height;
        rectBot = new Rectangle(x,y,width,height);
        this.direction = direction;
    }
    public Bullet(int x,int y, int width, int height,int direction, int fired)
    {
        this.firex=fired;
        this.x=x;
        this.y=y;
        x-=5;
        this.width = width;
        this.height =height;
        rectBot = new Rectangle(x,y,width,height);
        this.direction = direction;
    }
    public void update()
    {
        y+=1;
        updateRect();
    }public void updateBird()
    {

        if(direction == RIGHT)
            x+=1;
        else
            x-=1;
        updateRect();
    }

    public void updateRect()
    {
        rectBot.setLocation(x,y);
    }

    public int getX() {
        return x;
    }

    public void setX(double x) {
        this.x = (int)x;
        updateRect();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
