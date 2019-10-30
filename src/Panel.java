 /**
 * Created by othscs229 on 5/4/2017.
 */
/**
 * Created by othscs229 on 11/7/2016.
 */
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.awt.*;
import java.awt.event.*;;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class Panel extends JPanel implements KeyListener, Runnable
{
    public static final int PIPE=0;
    public static final int BONUS=1;
    public BufferedImage buffer = null;
    public BufferedImage bufferl = null;

    public boolean play = true;
    public double back;
    Bird bird;
    BufferedImage bird_img;
    BufferedImage sunset_img;
    BufferedImage ufo_img;
    BufferedImage target;
    public boolean gameOver;
    public long startTimeh=0;
    public boolean liftornah=false;
    public int highScore;
   public ArrayList<Pipe> pipes = new ArrayList<>();
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public int score;
    public int startTime=200;
    // sets the number of updates / frames per second
    private int updatesPerSecond = 15;
    public int mode;
    public ArrayList<Blob> blobs=null;
    public boolean stay;
    public UFO ufo;
    public long startBonus;
    public int stopUFOX;
    public int stopUFOY;

    public boolean showOption;
    public static final int STOP= 54;
    public static final int BOSS = 72;
    public BufferedImage alien;
        public BufferedImage alienflip;
    public BufferedImage birdBoss_img;
    public BufferedImage flappyshootflip;
    public int seconds;
    public int secondsDirection;
    boolean showOption2;
    boolean free;
    public static final int DESCENDING = 2343;
    public static final int RACE =9008;
    BufferedImage icon;
    BufferedImage nah;

    // number of updates
    private long updateCount = 0;
    public Panel(boolean free)
    {
        super();
        this.free=free;
        setSize(500,600);
        buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        bufferl = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
       try
       {
           Scanner kb = new Scanner(new File("high.txt"));
           highScore = kb.nextInt();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
        addKeyListener(this);
        try
        {
            bird_img = ImageIO.read(new File("flappy.png"));
            sunset_img = ImageIO.read(new File("Sunset.jpg"));
            ufo_img = ImageIO.read(new File("ufo.png"));
            target = ImageIO.read(new File("target.jpg"));
            alien = ImageIO.read(new File("alien.png"));
            birdBoss_img = ImageIO.read(new File("flappyshoot.png"));
            alienflip= ImageIO.read(new File("alienflip.png"));
           flappyshootflip = ImageIO.read(new File("flappyshootflip.png"));
            icon =  ImageIO.read(new File("speech.png"));
            nah = ImageIO.read(new File("nah.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        bird_img = scale(bird_img,100,100);
        birdBoss_img= scale(birdBoss_img,100,100);
        flappyshootflip = scale(flappyshootflip,100,100);
        //icon = scale(icon,100,100);
       // birdBoss_img = scale(birdBoss_img,100,100);
        old = ufo_img;
        target = colorToAlpha(target,new Color(target.getRGB(0,0)));
        target = scale(target,30,30);
        bird = new Bird();
        bird.setYpos(50);
        pipes.add(new Pipe());
        pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size()-1).x+300));
        pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size()-1).x+300));
        pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size()-1).x+300));
        pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size()-1).x+300));
        gameOver = false;
        score = 19;
        secondsDirection = 0;
        back = 2;
        seconds = 0;
        mode = PIPE;
        showOption2 = false;
        ufo = new UFO(bird.getXpos()-(ufo_img.getWidth()/2), 20);
        blobs = new ArrayList<>();
        stay=false;
        showOption=false;
        Thread t = new Thread(this);
        t.start();

    }
    public int startbirdx;
    public void paint(Graphics g)
    {

       // bg.setColor(new Color(bird_img.getRGB(0,0)));


        if(mode==PIPE) {


            Graphics bg = buffer.getGraphics();

            for (int i = 0; i < pipes.size(); i++) {
                pipes.get(i).setBack(back);
            }
            bg.drawImage(sunset_img, 0, 0, getWidth(), getHeight(), null);

          //  bg.setColor(Color.BLACK);
           // Rectangle r = bird.rect;
           // bg.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        //    bg.setColor(Color.ORANGE);
          //  Ellipse c = bird.cir;
            //bg.drawOval((int) c.getCenterX(), (int) c.getCenterY(), (int) c.getRadiusX(), (int) c.getRadiusY());



            bg.drawImage(colorToAlpha(bird_img, new Color(bird_img.getRGB(0, 0))), bird.xpos, (int) bird.ypos, null);
            ufo.setX(bird.getXpos()-(ufo_img.getWidth()/2));
            bg.drawImage(ufo_img, ufo.getX(), ufo.getY(), null);
            if (gameOver == false) {
                int add = (int) (Math.random() * 500);
                if (add == 1 && liftornah == false) {
                    startTimeh = System.nanoTime();
                    liftornah = true;
                    ufo_img = lighten(ufo_img, 1.5);

                    System.out.println("UFO Lit");
                }
                if (liftornah) {
                    int stop = (int) (Math.random() * 400);
                    if (stop==1&&bird.getYpos()>100) {
                        System.out.println("Stop getting lit");
                        bullets.add(bullets.size(), new Bullet(bird.getXpos() , 0 + ufo_img.getHeight()));
                        liftornah = false;
                        ufo_img = old;
                    }
                    else
                    {
                        bg.drawImage(target,bird.getXpos()+5,(int)bird.getYpos()+10,null);
                    }
                }
            }

            bg.setColor(new Color(0, 100, 0));
            for (int i = pipes.size() - 1; i >= 0; i--) {
                Pipe pipe = pipes.get(i);
                if (pipe.x < -100) {

                    pipes.remove(i);
                    pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                }
                    if(pipe.score==false &&((((pipe.x+ pipe.rectTop.width/2) + 10)>bird.xpos) &&   ((pipe.x+ pipe.rectTop.width/2) - 10<bird.xpos)))
                    {
                        pipes.get(i).setScore(true);
                    score += 1;
                    if(score<40&&score!=0&&score%10==0)
                    {
                        back+=.5;
                        System.out.println(back+"  ");

                    }

                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                                new File("wav//score.wav"));
                        clip.open(inputStream);
                        clip.start();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }

            for (int i = bullets.size() - 1; i >= 0; i--) {
                if (bullets.get(i).y > 620) {
                    bullets.remove(i);
                }
            }

            for (int i = pipes.size() - 1; i >= 0; i--) {
                Pipe pipe = pipes.get(i);
                bg.fillRect((int) pipe.rectTop.getX(), (int) pipe.rectTop.getY(), (int) pipe.rectTop.getWidth(), (int) pipe.rectTop.getHeight());

                bg.fillRect((int) pipe.rectBot.getX(), (int) pipe.rectBot.getY(), (int) pipe.rectBot.getWidth(), (int) pipe.rectBot.getHeight());

                if (bird.rect.intersects(pipe.rectBot) || bird.rect.intersects(pipe.rectTop)) {
                  gameOver = true;
                }
            }
            bg.setColor(Color.red);
            for (int i = bullets.size() - 1; i >= 0; i--) {

                Bullet pipe = bullets.get(i);

                bg.fillRect((int) pipe.rectBot.getX(), (int) pipe.rectBot.getY(), (int) pipe.rectBot.getWidth(), (int) pipe.rectBot.getHeight());

                if (bird.rect.intersects(bullets.get(i).rectBot)) {
                   gameOver = true;
                }
            }


            if (bird.ypos > (getHeight() - 1.1 * bird.rect.getHeight()) || bird.ypos < -100) {
                 gameOver=true;
            }


            bg.setFont(new Font("f", Font.BOLD, 60));
            bg.setColor(Color.BLACK);
            if (gameOver) {
                System.out.println("DEAD");
                if (play&&free) {
                    mode=BONUS;

                    blobs.clear();
                    for(int x=0;x<16;x++)
                    {
                        blobs.add(0,new Blob());
                    }

                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                                new File("wav//hurt.wav"));
                        clip.open(inputStream);
                        clip.start();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    play = false;
                    try {
                        bird.velocity=0;
                        bird.gravity=0;
                        Thread.sleep(1000);
                        bird.setXpos(30);
                        bird.setYpos(30);
                        bird.gravity=.1;
                        startBonus =System.nanoTime();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(!free)
                {
                    mode=STOP;
                    System.out.println(showOption+" ");
                    if(!showOption) {
                        showOption=true;
                    }
                }
                try {
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("high.txt"), "UTF-8"));
                    try {
                        pw.print(highScore + "");
                    } finally {
                        pw.close();
                    }
                } catch (Exception e) {
                    //  e.printStackTrace();
                }

                bg.drawString("Game Over", getWidth() / 2 - 100, 60);
                bg.drawString("Your Score: " + score, 50, 150);
                if(free)
                bg.drawString("High Score: " + highScore, 50, 240);
            } else {
                if (score > highScore)
                    highScore = score;
                bg.drawString(Math.round(score) + "", getWidth() / 2, 60);
                bg.setFont(new Font("fa", Font.BOLD, 30));
                if(free)
                bg.drawString(highScore + "", getWidth() / 2 + 7, 90);
            }
            if(score>19 && !free)
            {
//                System.out.println(!free);
//                mode = BOSS;
//                pipes.clear();
//                bullets.clear();
                bird.setXpos(30);
              //  ufo.setY((int)bird.getYpos());
               // ufo.setX(getWidth() - 200);
                mode=DESCENDING;
                bird.setYpos(getHeight()/2+20);
                stopUFOX = ufo.getX();
               stopUFOY= ufo.getY();
            }

            g.drawImage(buffer,0,0,null);
        }


        else if(mode==STOP)
        {
            Graphics bg = bufferl.getGraphics();
            bg.setColor(Color.BLACK);
            bg.fillRect(0,0,getWidth(),getHeight());
            g.drawImage(bufferl,0,0,null);
        }
        else if(mode == DESCENDING) {
            Graphics bg = bufferl.getGraphics();
            for (int i = 0; i < pipes.size(); i++) {
                pipes.get(i).setBack(back);
            }
            bg.drawImage(sunset_img, 0, 0, getWidth(), getHeight(), null);

            bg.setColor(Color.BLACK);

            //  bg.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());


            bg.drawImage(colorToAlpha(bird_img, new Color(bird_img.getRGB(0, 0))), bird.xpos, (int) bird.ypos, null);

            bg.drawImage(ufo_img, stopUFOX, stopUFOY, null);
            if (ufo.rectBot.getX() + ufo.width < getWidth()) {
                ufo.setX(ufo.getX() + 1);
            }
            if (ufo.rectBot.getY() + ufo.height + 50 < getHeight()) {
                ufo.setY(ufo.getY() + 1);
            }

            bg.drawImage(colorToAlpha(alien, new Color(bird_img.getRGB(0, 0))), ufo.getX(), ufo.getY(), null);



            if(Math.abs(ufo.getY()-bird.ypos)>(.5)*(bird.ypos)) {
                bg.drawImage(colorToAlpha(icon, new Color(icon.getRGB(0, 0))), ufo.getX() - 30 - alien.getWidth(), ufo.getY() - 30, this);

            }
            else
            {
                bg.drawImage(colorToAlpha(nah, new Color(icon.getRGB(0, 0))),(int) bird.rect.getX() + bird.rect.width ,(int) bird.rect.getY() - 30, this);
            }
            if(Math.abs(ufo.getY()-bird.ypos)<5)
            {
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                pipes.clear();
                bullets.clear();
                bird.liftf();
                mode=BOSS;
            }

            g.drawImage(bufferl,0,0,null);
        }

        else if(mode==BONUS)
        {
            Graphics bg = bufferl.getGraphics();



            bg.setColor(Color.ORANGE);
            bg.fillRect(0,0,getWidth(),getHeight());
            bg.setFont(new Font("fl", Font.BOLD, 60));
            bg.setColor(Color.WHITE);
            bg.drawString("BONUS",30,50);
            for(int x=0;x<blobs.size();x++){
                 if(bird.rect.intersects(blobs.get(x).rectBot)) {
                     blobs.remove(x);
                     score++;
                 }
            }



            bg.drawImage(colorToAlpha(bird_img, new Color(bird_img.getRGB(0, 0))), bird.xpos, (int) bird.ypos, null);

            if (score > highScore) {
                highScore = score;
                try {
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("high.txt"), "UTF-8"));
                    try {
                        pw.print(highScore + "");
                    } finally {
                        pw.close();
                    }
                } catch (Exception e) {
                    //  e.printStackTrace();
                }
            }
            bg.drawString(Math.round(score) + "", getWidth() / 2, 60);
            bg.setFont(new Font("fa", Font.BOLD, 30));

            bg.drawString(highScore + "", getWidth() / 2 + 7, 90);
            bg.setColor(Color.RED);
            bg.drawString((4 - Math.round(seconds/120f))+" seconds remaining",100,500);

            if(4 - Math.round(seconds/120f) <= 0)
            {
                mode=STOP;
    System.out.println(showOption+" ");
                if(!showOption) {
                            showOption=true;
                }

            }
            bg.setColor(Color.DARK_GRAY);
                for(int x=0;x<blobs.size();x++)
                {
                    bg.fillOval(blobs.get(x).getX(),blobs.get(x).getY(),blobs.get(x).getWidth(),blobs.get(x).getHeight());

                }

            g.drawImage(bufferl,0,0,null);


        }
        else if(mode == BOSS)
        {

            Graphics bg = bufferl.getGraphics();
            bg.drawImage(sunset_img, 0, 0, getWidth(), getHeight(), null);

            bg.setColor(Color.BLACK);
            bg.drawRect(ufo.rectBot.x,ufo.rectBot.y,ufo.rectBot.width,ufo.rectBot.height);

            bg.setFont(new Font("l",Font.ROMAN_BASELINE,30));
            bg.drawString("Ammo: ",10,getHeight()-25);

            bg.setColor(Color.RED);
            for(int x=0;x<(4-bird.bullets.size());x++)
            {
                System.out.println("bullet: "+x);
                bg.fillRect(x*50+100,getHeight()-50,40,20);
            }


            if(ufo.getX()>=bird.xpos) {
                bg.drawImage(colorToAlpha(alien, new Color(bird_img.getRGB(0, 0))), ufo.getX(), ufo.getY(), null);
                bg.drawImage(colorToAlpha(birdBoss_img, new Color(birdBoss_img.getRGB(0, 0))), bird.xpos, (int) bird.ypos, null);
            }
            else if(ufo.getX()<bird.xpos) {
                bg.drawImage(colorToAlpha(alienflip, new Color(bird_img.getRGB(0, 0))), ufo.getX(), ufo.getY(), null);
                bg.drawImage(colorToAlpha(flappyshootflip, new Color(birdBoss_img.getRGB(0, 0))), bird.xpos, (int) bird.ypos, null);
            }

            bg.setColor(Color.red);
            for (int i = bird.bullets.size() - 1; i >= 0; i--) {
                Bullet pipe = bird.bullets.get(i);
                bg.fillRect((int) pipe.rectBot.getX(), (int) pipe.rectBot.getY(), (int) pipe.rectBot.getWidth(), (int) pipe.rectBot.getHeight());
                if(pipe.rectBot.intersects(ufo.rectBot))
                {
                    ufo.decreaseHealth();
                    bird.bullets.remove(i);
                    ufo.offset=10;
                }
                if(pipe.getX()>getWidth()|| pipe.getX()<0)
                {
                    ufo.offset = 10;
                    if(pipe.direction== Bullet.LEFT) {
                        if(pipe.getX()<(0+.35*(pipe.firex-500))) {
                            bird.bullets.remove(i);
                        }
                    }
                    else
                    {
                        if(pipe.getX()>(pipe.firex + 500))
                        {
                            bird.bullets.remove(i);
                        }
                    }
                }
            }
            bg.setColor(Color.BLUE);
            for (int i = ufo.bullets.size() - 1; i >= 0; i--) {
                Bullet pipe = ufo.bullets.get(i);
                bg.fillRect((int) pipe.rectBot.getX(), (int) pipe.rectBot.getY(), (int) pipe.rectBot.getWidth(), (int) pipe.rectBot.getHeight());
                if(pipe.rectBot.intersects(bird.rect))
                {
                    bird.decreaseHealth();
                    ufo.bullets.remove(i);
                    break;
                }
                if(pipe.getX()>getWidth()|| pipe.getX()<0)
                {
                    ufo.bullets.remove(i);
                }
            }
            if(ufo.health<=0)
            {
               mode = RACE;
            }
            if(bird.health<=0)
            {
                showOption2 = true;
            }

            bg.setColor(Color.RED);
            bg.fillRect(ufo.rectBot.x-20,ufo.rectBot.y-20,100,5);
            bg.setColor(Color.GREEN);
            bg.fillRect(ufo.rectBot.x-20,ufo.rectBot.y-20,ufo.health,5);

            bg.setColor(Color.RED);
            bg.fillRect(bird.rect.x+20,bird.rect.y-50,100,5);
            bg.setColor(Color.GREEN);
            bg.fillRect(bird.rect.x+20,bird.rect.y-50,bird.health,5);


            g.drawImage(bufferl,0,0,null);
        }
        else if(mode==RACE)
        {

        }


    }
    public void keyPressed(KeyEvent e)
    {

        char key = e.getKeyChar();
        if(gameOver==false||  mode==BONUS  && mode!=DESCENDING) {
            if (key == 'd') {

                bird.moveRight();
            } else if (key == 'a') {

                bird.moveLeft();
            }
            if(key== 'w')
            {
//                try {
//                    Clip clip = AudioSystem.getClip();
//                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
//                            new File("wav//flap.wav"));
//                    clip.open(inputStream);
//                    clip.start();
//                } catch (Exception oe) {
//                    System.err.println(oe.getMessage());
//                }
                bird.lift();
                startTimeh =System.nanoTime();

            }
        }
    }
    public void keyReleased(KeyEvent e)
    {
        char key = e.getKeyChar();
        if(key == KeyEvent.VK_SPACE&&mode==BOSS && bird.bullets.size()<4)
        {
            if(ufo.getX()>=bird.xpos)
             bird.bullets.add(new Bullet(bird.getXpos()+bird_img.getWidth(),(int)bird.getYpos()-5,15,5,Bullet.RIGHT,bird.getXpos()));
            else if(ufo.getX()<bird.xpos)
                bird.bullets.add(new Bullet(bird.getXpos(),(int)bird.getYpos()-5,15,5,Bullet.LEFT,bird.getXpos()));
        }
        if(key== 'w' && (gameOver==false ||  mode==BONUS) && mode!=DESCENDING)
        {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        new File("wav//flap.wav"));
                clip.open(inputStream);
                clip.start();
            } catch (Exception oe) {
                System.err.println(oe.getMessage());
            }
           bird.liftf();
           startTimeh =System.nanoTime();

        }
        if(gameOver==false || mode==BONUS && mode!=DESCENDING) {
            if (key == 'd') {

                bird.moveRightf();
            } else if (key == 'a') {

                bird.moveLeftf();
            }
        }
        if( key=='n')
        {

           gameOver=false;
           score=0;
           pipes.clear();
           pipes.add(new Pipe());
            pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size()-1).x+300));
            pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size()-1).x+300));
            pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size()-1).x+300));
            pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size()-1).x+300));
           bullets.clear();
           bird= new Bird();
           bird.setYpos(50);
           startTimeh = 0;
           ufo_img = old;
           back=2;
           seconds=0;
           liftornah =false;
           mode=PIPE;
            play=true;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {


    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }
    public static BufferedImage colorToAlpha(BufferedImage raw, Color remove)
    {
        int WIDTH = raw.getWidth();
        int HEIGHT = raw.getHeight();
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        int pixels[]=new int[WIDTH*HEIGHT];
        raw.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
        for(int i=0; i<pixels.length;i++)
        {
            if (pixels[i] == remove.getRGB())
            {
                pixels[i] = 0x00ffffff;
            }
        }
        image.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
        return image;
    }
    public static BufferedImage scale(BufferedImage img,int newWidth, int newHeight)
    {

        int scaleX = newWidth;
        int scaleY = newHeight;
        Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
        BufferedImage buffered = new BufferedImage(scaleX, scaleY,BufferedImage.TYPE_INT_ARGB);
        buffered.getGraphics().drawImage(image, 0, 0 , null);

        return buffered;
    }

    public static BufferedImage lighten(BufferedImage raw, double darkenFactor)
    {
        int WIDTH = raw.getWidth();
        int HEIGHT = raw.getHeight();
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(raw,0,0,null);
        for(int x =0;x<raw.getWidth();x++)
        {
            for (int y=0;y<raw.getHeight();y++)
            {
                if(image.getRGB(x,y)!=0) {
                    Color c = new Color(image.getRGB(x, y));
                    int r=(int)(c.getRed()*(1+darkenFactor));
                    int g = (int)(c.getGreen()*(1+darkenFactor));
                    int b = (int)(c.getBlue()*(1+darkenFactor));
                    if(r>255)
                        r=255;
                    if(g>255)
                        g=255;
                    if(b>255)
                        b=255;
                    Color newc = new Color(r,g,b);

                    image.setRGB(x, y, newc.getRGB());
                }
            }
        }
        return image;
    }

public int dirUFO;
    BufferedImage old;
    public void run()
    {

        // calculates how many miliseconds to wait for the next update
        int waitToUpate = (150/ updatesPerSecond);

        long startTime = System.nanoTime();

        while(true)
        {
            // is true when you update
            boolean shouldRepaint = false;

            // Finds the current time
            long currentTime = System.nanoTime();

            // Finds out how many updates are needed
            long updatesNeeded = (((currentTime-startTime) / 1000000))/ waitToUpate;
            for(long x = updateCount; x< updatesNeeded; x++)
            {
                if(gameOver==false) {
                    if(mode==PIPE) {

                        for (int i = 0; i < pipes.size(); i++) {
                            pipes.get(i).update();
                        }
                        for (int i = 0; i < bullets.size(); i++) {
                            bullets.get(i).update();
                        }
                    }


                }
                if(mode==BONUS) {
                    //  bird.gravity=0;
                    seconds++;
                }
                if(mode==BOSS)
                {

                    if(secondsDirection%50==0) {
                        dirUFO = (int) (Math.random() * 8);
                    }
                    ufo.update(bird,dirUFO);

                        for (int i = 0; i < bird.bullets.size(); i++) {
                            bird.bullets.get(i).updateBird();
                        }
                    for (int i = 0; i < ufo.bullets.size(); i++) {
                        ufo.bullets.get(i).updateBird();
                    }
                        secondsDirection++;

                }
                if(mode==STOP || mode == BOSS)
                {
                    Frame frame = new Frame();
                    if(showOption)
                    {
                        while(showOption) {
                           if(mode==STOP) {
                               if (frame.showOrNah(score)) {
                                   showOption = false;
                                   gameOver = false;
                                   score = 0;
                                   pipes.clear();
                                   pipes.add(new Pipe());
                                   pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                                   pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                                   pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                                   pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                                   bullets.clear();
                                   bird = new Bird();
                                   bird.setYpos(50);
                                   startTimeh = 0;
                                   ufo_img = old;
                                   back = 2;
                                   seconds = 0;
                                   liftornah = false;
                                   //   mode = PIPE;
                                   Window w = SwingUtilities.getWindowAncestor(this);
                                   w.dispose();
                                   play = true;
                                   frame.setVisible(false);
                               }
                           }
                           else if(showOption)
                           {
                               if (frame.showMessage(score)) {
                                   showOption = false;
                                   gameOver = false;
                                   score = 0;
                                   pipes.clear();
                                   pipes.add(new Pipe());
                                   pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                                   pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                                   pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                                   pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                                   bullets.clear();
                                   bird = new Bird();
                                   bird.setYpos(50);
                                   startTimeh = 0;
                                   ufo_img = old;
                                   back = 2;
                                   seconds = 0;
                                   liftornah = false;
                                   //   mode = PIPE;
                                   Window w = SwingUtilities.getWindowAncestor(this);
                                   w.dispose();
                                   play = true;
                                   frame.setVisible(false);
                               }
                           }

                       }
                    }

                    else if(showOption2)
                    {
                        if (frame.showMessage2(score)) {
                            showOption = false;
                            showOption2=false;
                            gameOver = false;
                            score = 0;
                            pipes.clear();
                            pipes.add(new Pipe());
                            pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                            pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                            pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                            pipes.add(pipes.size(), new Pipe(pipes.get(pipes.size() - 1).x + 300));
                            bullets.clear();
                            bird = new Bird();
                            bird.setYpos(50);
                            startTimeh = 0;
                            ufo_img = old;
                            back = 2;
                            seconds = 0;
                            liftornah = false;
                            //   mode = PIPE;
                            Window w = SwingUtilities.getWindowAncestor(this);
                            w.dispose();
                            play = true;
                            frame.setVisible(false);
                        }
                    }
                }
                if(mode!= STOP && mode !=DESCENDING) {
                    bird.update();
                }
                shouldRepaint=true;
                updateCount++;
            }


            if(shouldRepaint)
                repaint();


            // sleep so other threads have time to run
            try
            {
                Thread.sleep(5);
            }
            catch(Exception e)
            {
                System.out.println("Error sleeping in run method: " +e.getMessage());
            }
        }
    }
}