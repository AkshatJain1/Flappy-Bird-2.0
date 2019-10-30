import java.awt.*;
import java.util.ArrayList;

/**
 * Created by respe on 5/15/2017.
 */
public class UFO {
    int xp;
    int y;
    int width;
    int height;
    public int health;
    public Rectangle rectBot;
    public int offset;
    public ArrayList<Bullet> bullets;
    public UFO(int xp,int y)
    {
        this.xp=xp;
        this.y=y;
        xp-=5;
        width=40 ;
        height =90;
        rectBot = new Rectangle(xp+40,y+10,width,height);
        health = 100;
        offset=10;
        bullets = new ArrayList<>();
    }
    public void update(Bird b,int d) {
        // setY((int)b.ypos);
        int count = 0;
        for (int x = 0; x < b.bullets.size(); x++) {
            Bullet bull = b.bullets.get(x);

            //if bullet is within 40 pixels of x and 15 pixels of y
            if ((Math.abs(b.bullets.get(x).getX() - rectBot.getX()) < 40 || Math.abs(b.bullets.get(x).getX() - (rectBot.getX() + width)) < 40) && Math.abs(bull.getY() - getMiddleY()) < 15 + height){
            count++;
        }
    }
    if(Math.abs(b.rect.getY() - getMiddleY())<2&& bullets.size()<6) {
        if (b.rect.getX() < rectBot.getX()) {
            bullets.add(new Bullet(getX(),getMiddleY(),15,5,Bullet.LEFT));
        }
        if (b.rect.getX() > rectBot.getX()) {
            bullets.add(new Bullet(getX()+2*width+40,getMiddleY(),15,5,Bullet.RIGHT));
        }
    }

    if(count==0)
        {
            if(Math.abs(b.getXpos()-xp)<50)
            {
                if(b.getXpos()>xp)
                {
                    if(500-(xp+width)<20)
                    {
                        y+=2;
                        xp+=2;
                    }
                    else {
                        y+=2;
                        xp -= 2;
                    }
                }
                else
                {
                    if(Math.abs(500-(xp+width))<40)
                    {
                        y-=2;
                            xp-=2;
                    }
                    else {
                        y-=2;
                        xp += 2;
                    }
                }
            }
            else {
                if (d == 0 && xp + width < 500) {
                    if (b.getXpos() < xp)
                        xp += (int) (Math.random() * 1);
                    else
                        xp += (int) (Math.random() * 2);
                } else if (d == 1 && xp > 0) {
                    if (b.getXpos() < xp)
                        xp += (int) (Math.random() * -2);
                    else
                        xp += (int) (Math.random() * -1);
                } else if (d == 2 && y + height < 600) {
                    y += (int) (Math.random() * 2);
                } else if (d == 3 && y > 0) {
                    y += (int) (Math.random() * -2);
                } else if (d == 4 && y + height < 600 && xp + width < 500) {
                    xp += (int) (Math.random() * 2);
                    y += (int) (Math.random() * 2);
                } else if (d == 5 && y > 0 && xp > 0) {
                    xp += (int) (Math.random() * -2);
                    y += (int) (Math.random() * -2);
                } else if (d == 6 && xp + width < 50 && y > 0) {
                    xp += (int) (Math.random() * 2);
                    y += (int) (Math.random() * -2);
                } else if (d == 7 && xp > 0 && y + height < 600) {
                    xp += (int) (Math.random() * -2);
                    y += (int) (Math.random() * 2);
                }
            }

        }
        else {
            for (int x = 0; x < b.bullets.size(); x++) {
                Bullet bull = b.bullets.get(x);

                //if bullet is within 40 pixels of x and 15 pixels of y
                if ((Math.abs(b.bullets.get(x).getX() - rectBot.getX()) < 40 || Math.abs(b.bullets.get(x).getX() - (rectBot.getX() + width)) < 40) && Math.abs(bull.getY() - getMiddleY()) < 15 + height) {


                    // bullet on top
                    if (bull.getY() - getMiddleY() <= 0) {
                        if (rectBot.getY() + height < 600 - offset) {

                            y += 2;
                        } else if (Math.abs(bull.getY() - getMiddleY()) < height / 2) {

                            if (b.getXpos() <= rectBot.getX())
                                xp += 3;
                            else
                                xp -= 3;
                            y -= 6;
                            offset += 7;

                        }
                    }
                    //bullet on bot
                    else if (bull.getY() - getMiddleY() > 0) {
                        if (rectBot.getY() > offset) {

                            y -= 2;
                        } else if (bull.getY() - getMiddleY() < height / 2) {

                            if (b.getXpos() <= rectBot.getX())
                                xp += 3;
                            else
                                xp -= 3;
                            offset += 7;
                            y += 6;
                        }
                    }


                }


            }
        }
        updateRect();
    }
    public void updateRect()
    {
        rectBot.setLocation(xp+40,y+10);
    }
    public void decreaseHealth()
    {
        health-=10;
    }

    public int getX() {
        return xp;
    }

    public void setX(double xp) {
        this.xp = (int)xp;
        updateRect();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getMiddleY()
    {
        return rectBot.y + height/2;
    }

    public boolean inBounds()
    {
        if(xp>0 && xp+width<500 && y>0 && y+height<600)
            return true;
        else
            return false;
    }

    }

