import java.awt.Color;

//UIUC CS125 FALL 2013 MP. File: RainGame.java, CS125 Project: PairProgramming, Version: 2013-10-01T11:04:49-0500.033586324
/**
 * @author dprorok2, kortend2
 */
public class RainGame {

	public static void main(String[] args) {
		// To get points type your netids above (CORRECTLY-Please double check your partner correctly spells your netid correctly!!)
		// Your netid is the unique part of your @illinois email address
		// Do not put your name or your UIN. 
		// REMEMBER TO COMMIT this file...
	
		int x=0, y=0, dx=1, dy=1, dxSign=1, dySign=1,score = -1000000, level = 0;
		Color textColor= new Color(255, 255, 255);
		Color backgroundColor=new Color(0,0,0);
		String text = "";
		boolean leveledDown=false;
		boolean leveledUp=false;
		long startTime =System.currentTimeMillis();
		
		Zen.setFont("Helvetica-64");
		while (Zen.isRunning()) {
			
			if(leveledUp)
				Zen.sleep(200);
				
			if(leveledDown)
				Zen.sleep(500);
			
			leveledDown=false;
			leveledUp=false;
			
			if (text.length() == 0) {
				if(level<70)
					x = Zen.getZenWidth()/2-10*(level/10)-15;
				else
					x = Zen.getZenWidth()/2-15*(6);
				y = Zen.getZenHeight() / 2;
				if(Math.random()>.5)
					dxSign*=-1;
				if(Math.random()>.5)
					dySign*=-1;
				if(level<15)
				{
				dx = dxSign*(level+(int) (Math.random() * 5));
				dy = dySign*(level+(int) (Math.random() * 5));
				}
				else
				{
					dx = dxSign*(15+(int) (Math.random() * 2));
					dy = dySign*(15+(int) (Math.random() * 2));
				}
				if(level<70)
				text = "" + (int) (Math.random() * (999*(Math.pow(10, level/10))));
				else
					text = "" + (int) (Math.random() * (999*(Math.pow(10, 6))));
				long elapsed = System.currentTimeMillis() - startTime;
				startTime = System.currentTimeMillis();
				score += (10000 / elapsed)*level;
				if(score<0)score=0;
				level++;
				if(level>5)
				textColor= new Color((int) (Math.random() * 255),(int) (Math.random() * 255),(int) (Math.random() * 255));
				if (level>10)
				backgroundColor = new Color((int) (Math.random() * 255),(int) (Math.random() * 255),(int) (Math.random() * 255));
			}

			if(Zen.isKeyPressed('+')||Zen.isKeyPressed('='))
			{
				level++;
				if(level<70)
					x = Zen.getZenWidth()/2-10*(level/10)-15;
				else
					x = Zen.getZenWidth()/2-15*(6);
				y = Zen.getZenHeight() / 2;
				if(Math.random()>.5)
					dxSign*=-1;
				if(Math.random()>.5)
					dySign*=-1;
				if(level<15)
				{
				dx = dxSign*(level+(int) (Math.random() * 5));
				dy = dySign*(level+(int) (Math.random() * 5));
				}
				else
				{
					dx = dxSign*(15+(int) (Math.random() * 2));
					dy = dySign*(15+(int) (Math.random() * 2));
				}
				if(level<70)
					text = "" + (int) (Math.random() * (999*(Math.pow(10, level/10))));
				else
					text = "" + (int) (Math.random() * (999*(Math.pow(10, 6))));
				if(level>5)
				textColor= new Color((int) (Math.random() * 255),(int) (Math.random() * 255),(int) (Math.random() * 255));
				if (level>10)
				backgroundColor = new Color((int) (Math.random() * 255),(int) (Math.random() * 255),(int) (Math.random() * 255));
				leveledUp=true;
			}
			
			if(Zen.isKeyPressed('\n')&&!leveledDown)
			{
				if(level>1)
				level--;
				if((score-(int)(score/(level))>0))
				score-=(int)(score/(level));
				else score=0;
				
				x = Zen.getZenWidth()/2-5*level/10;
				y = Zen.getZenHeight() / 2;
				if(Math.random()>.5)
					dxSign*=-1;
				if(Math.random()>.5)
					dySign*=-1;
				if(level<20)
				{
				dx = dxSign*(level);
				dy = dySign*(level);
				}
				if(level>5)
				textColor= new Color((int) (Math.random() * 255),(int) (Math.random() * 255),(int) (Math.random() * 255));
				leveledDown=true;
			}
			Zen.flipBuffer();
			
			if(level<=10)
			backgroundColor=new Color(0,0,0);
			Zen.setColor(backgroundColor);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			if(level<=5)
				textColor= new Color(255, 255, 255);
			Zen.setFont("Helvetica-32");
			Zen.setColor(textColor);
			Zen.drawText(text, x, y);
			Zen.setFont("Helvetica-64");
			
			Zen.drawText("Level: "+level,10,50);
			Zen.drawText("Score: "+score,10,100);
			
			Zen.setFont("Helvetica-22");
			Zen.setColor(255,255,255);
			Zen.drawText("Press enter if you miss a number. Press plus key to increase level.",0,Zen.getZenHeight()-3);
			Zen.setFont("Helvetica-64");
			
			x += dx;
			y += dy;
			
			// Find out what keys the user has been pressing.
			String user = Zen.getEditText();
			// Reset the keyboard input to an empty string
			// So next iteration we will only get the most recently pressed keys.
			Zen.setEditText("");
			
			for(int i=0;i < user.length();i++) {
				char c = user.charAt(i);
				if(c == text.charAt(0))
					text = text.substring(1,text.length()); // all except first character
				else if(score-level>0&&c!='+'&&c!='=')score -=level/2;
				else if(c!='+'&&c!='=')score=0;
			}

				Zen.sleep(90);// sleep for 90 milliseconds
		}
	}

}
