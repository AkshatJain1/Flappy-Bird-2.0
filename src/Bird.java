import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by othscs229 on 5/4/2017.
 */
public class Bird {
    public int xpos;
    public double ypos;
    public static final double lift=.5;
    public   double gravity=.1;
    public double velocity;
    public Rectangle rect;
    public Ellipse cir;

    public boolean right;
    public boolean left;
    public boolean liftbol;
    public ArrayList <Bullet> bullets;
    public int health;
    public Bird()
    {
        xpos=25;
        ypos=0;
        rect = new Rectangle(xpos+35,(int)ypos+28,35,42);
        cir = new Ellipse(xpos,ypos,35,42);

        velocity=0;
        right =false;
        left=false;
        liftbol=false;
        bullets = new ArrayList<>();
        health=100;
    }
    public void decreaseHealth()
    {
        health-=13;
    }
    public int getXpos() {
        return xpos+35;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public double getYpos() {
        return ypos+28;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
        updateRect();
    }
    public void moveRight()
    {
        right=true;
        updateRect();
    }
    public void moveLeft()
    {
       left=true;
        updateRect();
    }
    public void moveRightf()
    {
       right=false;
        updateRect();
    }
    public void moveLeftf()
    {
        left=false;
        updateRect();
    }


    public void update()
    {


        if(right)
    {
        xpos+=3;
    }
    else if(left)
    {
        xpos-=3;
    }
        if(liftbol)
        {
            velocity-=lift;
        }
        velocity+=gravity;
        if(liftbol)
    velocity*=.9;
        ypos+=velocity;


     //   velocity=0;

        for (int i = bullets.size() - 1; i >= 0; i--) {
            if (bullets.get(i).x > 500) {
                bullets.remove(i);
            }
        }
        updateRect();

    }

    public void lift()
    {

       liftbol=true;
        updateRect();
    }
    public void liftf()
    {

        liftbol=false;
        updateRect();
    }
    public void updateRect()
    {
        cir.setCenterX(xpos+35);
        cir.setCenterY(ypos+28);
        rect.setLocation(xpos+35,(int)ypos+28);
    }
}
