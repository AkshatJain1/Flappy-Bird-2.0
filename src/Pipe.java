import java.awt.*;

/**
 * Created by othscs229 on 5/4/2017.
 */
public class Pipe {
    public double x;
    public int space;
    private int heightOnTop =0;
    private int heightOnBottom =0;
    public Rectangle rectTop;
    public Rectangle rectBot;
    public boolean score;


    public int getHeightOnTop() {
        return heightOnTop;
    }

    public int getHeightOnBottom() {
        return heightOnBottom;
    }

    public  double back =3;

    public double getBack() {
        return back;
    }

    public void setBack(double back) {
        this.back = back;
    }

    public Pipe(){
        x=1000;
        space = (int)(Math.random()*50 +150 );


        while((600-(heightOnBottom + heightOnTop))!=space )
        {
            heightOnTop = (int)(Math.random()*600);
            heightOnBottom = (int)(Math.random()*(600-heightOnTop));

        }

        makeRect();
        score=false;

    }
    public Pipe(double x){
        this.x=x;
        space = (int)(Math.random()*50 +150 );


        while((600-(heightOnBottom + heightOnTop))!=space )
        {
            heightOnTop = (int)(Math.random()*600);
            heightOnBottom = (int)(Math.random()*(600-heightOnTop));

        }

score=false;
     makeRect();


    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }
    public  void update()
    {
        x-=back;
        updateRect();
    }
    public void updateRect()
    {
        rectBot.setLocation((int)x,600-getHeightOnBottom());
        rectTop.setLocation((int)x,0);
    }
    public void makeRect()
    {
        rectTop=new Rectangle(50,getHeightOnTop());
        rectBot = new Rectangle(50,getHeightOnBottom());
        updateRect();
    }

    public boolean isScore() {
        return score;
    }

    public void setScore(boolean score) {
        this.score = score;
    }
}
