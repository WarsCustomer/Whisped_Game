import java.awt.Graphics;
public class Hero {
    
    TLoader tl = new TLoader("src/");
    public double x = 200; // coors
    public float y = 6340; // coors
    public byte dir = 1; //hero's direction 
    public float vy = 0, ay = 0.7f;// motion options
    public byte vx = 0;
    int t = 0, p = 0;
    Enemy e;
    Game game;
    
    public void tick(World w) {
        y += vy;
        vy += ay;
        
      int bx = (int)(x/(float)w.s),  by =(int)(y/(float)w.s);
      int b = w.get(bx, by);
      if (b != 0 & b !=19 & b !=27) {
          x = bx * w.s + w.s;
      }
        
      if (w.get((int) Math.round((x) / (float) w.s), (int) (y / (float) w.s) + 1) >= 1  &  w.get((int) Math.round((x) / (float) w.s), (int) (y / (float) w.s) + 1) <= 10) {
        if (w.get((int) Math.round((x) / (float) w.s), (int) (y / (float) w.s) + 1) != 19) {
          y = (int) (y / 64) * 64;
        }
        vy = 0;
      }
    
      if (w.get((int) Math.round((x)/ (float) w.s ), (int) (y / (float) w.s - 0.2)) == 1 )
          vy = (int)(ay + 0.5);
      
      if (vy > 32)
          vy = 32;
      
      if (w.get((int) (((x) / (float) w.s) + 0.5), (int) (y / (float) w.s)) == 19) {
            ay = 0;
        } else {
          ay = 0.7f;
      }
      
      if (t > 0) 
            t--;
      if (p > 0)
          p--;
      
      double d = 100;
            this.e = null;
            for (Enemy e : game.mafias){
            if(Math.abs(e.x - x)<d & e.y ==y){
            d = Math.abs(e.x - x);  
            this.e = e;
        }}
      
}
    
   public void render(Graphics g, int c, int bt) {
        if (Math.abs(vy) < 15) {
            if (vx == 1) {
                dir = 1;
                g.drawImage(tl.im("~/res/hero/right/g" + ((bt - (bt / 20) * 20) / 4) + ".png"), 242, (int) y - c, 64, 64, null);
                vx = 0;
            } else if (vx == -1) {
                dir = -1;
                g.drawImage(tl.im("~/res/hero/left/g" + ((bt - (bt / 20) * 20) / 4) + ".png"), 242, (int) y - c, 64, 64, null);
                vx = 0;
            } else {
                switch (dir) {
                    case -1:
                        g.drawImage(tl.im("~/res/hero/left/g0.png"), 242, (int) y - c, 64, 64, null);
                        break;
                    case 1:
                        g.drawImage(tl.im("~/res/hero/right/g0.png"), 242, (int) y - c, 64, 64, null);
                        break;
                }
            }

        } else {
            g.drawImage(tl.im("~/res/hero/gj" + ((bt - (bt / 20) * 20) / 4) + ".png"), 242, (int) y - c, 64, 64, null);
            if (((bt - (bt / 20) * 20) / 4) < 4) {
                bt--;
            }
        }
    }
     
    public void jump(World w) {
        if (w.get((int) Math.round((x)/ (float) w.s ), (int) (y / (float) w.s + 1)) == 1 )
            vy = -15;
    }
    
    public void attack() {
        if(e !=null) {
            e.hp-=1;
        }
    }
    
}
