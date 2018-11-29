import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class gameSetUp implements Runnable {
	
	private String title;
    private int width;
    private int height;
    

    private BufferStrategy buffer;
    private Graphics g;
    private int y;
    private boolean start;
    private gameManager manager;
   
    private Display display;
    public static  int gameWidth = 400;
    public static  int gameHeight = 400;
   
    public gameSetUp(String title,int width,int height){
   
       this.title = title;
       this.width = width;
       this.height = height;
   
    }
 /* init method is used to run things which are to be run only onece*/   
      public void init(){
          
        display = new Display(title,width,height);
   
        manager = new gameManager();
        manager.init();
        start = true;
         }

      //things which are need to be refre
     public void tick(){
        manager.tick();
       }
     
     public void render(){
      
       buffer = display.getCanvas().getBufferStrategy();
       if(buffer == null){
         display.getCanvas().createBufferStrategy(3);
         return;
       }
     
       
       
      g = buffer.getDrawGraphics();
      g.clearRect(0, 0, width, height);
      //draw  
      g.setColor(Color.WHITE);
     //  g.drawImage(loadImage.image,50,50, gameWidth, gameHeight,null);
      g.fillRect(50, 50, gameWidth, gameHeight);//game screen
        
         manager.render(g);      
       // menu

     //end of draw
        
      buffer.show();
      g.dispose();
        
     }
       
     
    public void run() 
    {
        init();
            
        int fps = 50;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long current = System.nanoTime();
        

         while(true){
      
        	 	delta = delta + (System.nanoTime()-current)/timePerTick;
        	 	current = System.nanoTime();
        	 	if(delta>=1)
        	 	{
            	
        	 		tick();
        	 		render();
        	 		delta--;
        	 	}
   
         	}
    }


}
