package Janna_Eating;
import javax.swing.*;

import Home.MainPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

public class GamePanel5_Settings extends JPanel {

	 int cat1Y = 420, cat1X = 337;
	    String cat1Direction = "still";

	    Image backgroundImage;
	    
	    JLabel scoreLabel, timerLabel, gameOverLabel, scoreLabel2;
	    
	    Font lblFont, lblFont2;
	    
	    Timer gameTimer, countdownTimer;
	    
	    JButton backButton, restartButton;
	    
	    GamePanel5_Sound soundPlayer;
	    
	    int timeRemaining = 60;
	    int score = 0;
	    int score2 = 5;
	    
	    GamePanel5_Character cat1, cat2, cat3;

	    // Edible
	    GamePanel5_Food cfMilk, cfBread, cfFish, cfCatFood, cfHotdog, cfMice;

	    // Not edible
	    GamePanel5_Food coBall, coCan, coChoco, coGrapes, coMug, coSpicy;

    public GamePanel5_Settings(MainPanel mp) {
    	this.setOpaque(false);
        this.setLayout(null);
        
        gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setFont(mp.arcade.deriveFont(Font.BOLD, 34));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setForeground(new Color(191, 210, 130));
        gameOverLabel.setBounds(240, 100, 300, 50);
        gameOverLabel.setVisible(false);
        add(gameOverLabel);
        
        restartButton = new JButton("Restart");
        restartButton.setBounds(320, 400, 160, 43);
        restartButton.setVisible(false);
        restartButton.setBorderPainted(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setOpaque(false);
        restartButton.setIcon(mp.resizeImage("/start_button.png", 160, 43));
        mp.addHoverEffect(restartButton, 320, 400, 160, 43, 180, 63, "/start_button.png");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	resetGame();
            	startGame();
            }
        });
        add(restartButton);

        // Score label
        scoreLabel2 = new JLabel();
        scoreLabel2.setFont(mp.arcade.deriveFont(Font.PLAIN, 24));
        scoreLabel2.setForeground(new Color(191, 210, 130));
        scoreLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel2.setBounds(240, 150, 300, 30);
        scoreLabel2.setVisible(false);
        add(scoreLabel2);
        
        soundPlayer = new GamePanel5_Sound();
        
        Random rand = new Random();

        // Load background image
        URL urlBg = GamePanel5_Settings.class.getResource("/bgg.png");
        backgroundImage = new ImageIcon(urlBg).getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        
        //Font
        try {
        	InputStream is = getClass().getResourceAsStream("/Fonts/ka1.ttf");

        	if (is != null) {
        	    lblFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f);
        	    lblFont2 = lblFont.deriveFont(36f);
        	} else {
        	    System.err.println("Font not found!");
        	}
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(lblFont);
            ge.registerFont(lblFont2);
            
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            lblFont = new Font("Arial", Font.BOLD, 16);
            lblFont2 = new Font("Arial", Font.BOLD, 36);
        }


        // Score Label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 50, 200, 30); 
        scoreLabel.setForeground(new Color(131, 18, 172));
        scoreLabel.setFont(mp.arcade.deriveFont(Font.PLAIN, 14));
        this.add(scoreLabel);

        //Timer Label
        timerLabel = new JLabel("Time: 60");
        timerLabel.setBounds(10, 70, 200, 30);
        timerLabel.setForeground(new Color (131, 18, 172));
        timerLabel.setFont(mp.arcade.deriveFont(Font.PLAIN, 14)); 
        this.add(timerLabel);
       
        //Back Button
        backButton = new JButton();
        backButton.setIcon(mp.resizeImage("/back_button.png", 120, 45));
        backButton.setFont(new Font("Pixel Sans Serif", Font.PLAIN, 32));
        
        // Remove default button border
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);
        
        mp.addHoverEffect(backButton, 10, 10, 120, 45, 130, 55, "/back_button.png");
        backButton.setBounds(10, 10, 120, 45); 
        this.add(backButton);
        
        backButton.addActionListener(e ->{
        mp.showScreen("LOADING5");
        stopTime();
    });
        

        // Create food items
        cfMilk = new GamePanel5_Food("milk");
        cfBread = new GamePanel5_Food("bread");
        cfFish = new GamePanel5_Food("fish");
        cfCatFood = new GamePanel5_Food("catFood");
        cfHotdog = new GamePanel5_Food("hotdog");
        cfMice = new GamePanel5_Food("mice");

        cfMilk.setBounds(100, 20, 50, 50);
        cfBread.setBounds(80, 50, 50, 50);
        cfFish.setBounds(140, 20, 50, 50);
        cfCatFood.setBounds(200, 50, 50, 50);
        cfHotdog.setBounds(260, 20, 50, 50);
        cfMice.setBounds(320, 50, 50, 50);

        this.add(cfMilk);
        this.add(cfBread);
        this.add(cfFish);
        this.add(cfCatFood);
        this.add(cfHotdog);
        this.add(cfMice);

        // Create object items
        coBall = new GamePanel5_Food("ball");
        coCan = new GamePanel5_Food("can");
        coChoco = new GamePanel5_Food("choco");
        coGrapes = new GamePanel5_Food("grapes");
        coMug = new GamePanel5_Food("mug");
        coSpicy = new GamePanel5_Food("spicy");

        coBall.setBounds(380, 20, 50, 50);
        coCan.setBounds(440, 50, 50, 50);
        coChoco.setBounds(500, 20, 50, 50);
        coGrapes.setBounds(620, 50, 50, 50);
        coMug.setBounds(680, 20, 50, 50);
        coSpicy.setBounds(740, 50, 50, 50);

        this.add(coBall);
        this.add(coCan);
        this.add(coChoco);
        this.add(coGrapes);
        this.add(coMug);
        this.add(coSpicy);
        
        // Create character
        cat1 = new GamePanel5_Character();
        this.add(cat1);
        
        cat2 = new GamePanel5_Character();
        this.add(cat2);
        
        cat3 = new GamePanel5_Character();
        this.add(cat3);

        // Timer for "cat-ch the food!" game 
        gameTimer = new Timer(1000 / 60, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                switch (cat1Direction) {
                    case "right":
                        if (cat1X < getWidth() - 109) cat1X += 20;
                        break;
                    case "left":
                        if (cat1X > 0) cat1X -= 10;
                        break;
                }
                cat1.setBounds(cat1X, cat1Y, 109, 155);
                checkCollisions();
                updateFoodPositions();
                repaint();
                setFocusable(true);
                requestFocusInWindow();
            }
        });

        // Timer for countdown
        countdownTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time: " + timeRemaining);
                if (timeRemaining <= 0) {
                    endGame();
                }
            }
        });
        // Cat Movement
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        cat1Direction = "right";
                        break;
                    case KeyEvent.VK_LEFT:
                        cat1Direction = "left";
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cat1Direction = "still";
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    public JButton getBackButton() {
        return backButton;
    }
    
    public void stopTime() {
    	gameTimer.stop();
        countdownTimer.stop();
    }

    private void endGame() {
        gameTimer.stop();
        countdownTimer.stop();

        restartButton.setVisible(true);
        gameOverLabel.setVisible(true);
        scoreLabel2.setVisible(true);
        
        if (score < 0) {
        	scoreLabel2.setText("Score: 0");
        	scoreLabel.setText("Score: 0"); 
        }else {
        scoreLabel2.setText("Score: " + score);
        scoreLabel.setText("Score: " + score); 
        }
        
        soundPlayer.setSound(5);
        soundPlayer.playSound();

        this.repaint();
    }


    private void resetGame() {
    	restartButton.setVisible(false);
    	resetFoodPositions();
        timeRemaining = 60;
        score = 0;
        scoreLabel.setText("Score: 0"); 
        scoreLabel.setVisible(true); 
        timerLabel.setText("Time: 60");
        cat1X = 337;
        cat1Y = 420;

        scoreLabel2.setVisible(false);
        gameOverLabel.setVisible(false);
       
        this.repaint();
    }

    public void startGame() {
        resetGame();
        gameTimer.start();
        countdownTimer.start();
    }

    public void resetFoodPositions() {
    	cfMilk.setBounds(100, 20, 50, 50);
        cfBread.setBounds(80, 50, 50, 50);
        cfFish.setBounds(140, 20, 50, 50);
        cfCatFood.setBounds(200, 50, 50, 50);
        cfHotdog.setBounds(260, 20, 50, 50);
        cfMice.setBounds(320, 50, 50, 50);
        
        coBall.setBounds(380, 20, 50, 50);
        coCan.setBounds(440, 50, 50, 50);
        coChoco.setBounds(500, 20, 50, 50);
        coGrapes.setBounds(620, 50, 50, 50);
        coMug.setBounds(680, 20, 50, 50);
        coSpicy.setBounds(740, 50, 50, 50);
    }
	private void updateFoodPositions() {
        // Falling food and objects
        cfMilk.setBounds(cfMilk.getX(), cfMilk.getY() + 5, 50, 50);
        cfBread.setBounds(cfBread.getX(), cfBread.getY() + 5, 50, 50);
        cfFish.setBounds(cfFish.getX(), cfFish.getY() + 5, 50, 50);
        cfCatFood.setBounds(cfCatFood.getX(), cfCatFood.getY() + 5, 50, 50);
        cfHotdog.setBounds(cfHotdog.getX(), cfHotdog.getY() + 5, 50, 50);
        cfMice.setBounds(cfMice.getX(), cfMice.getY() + 5, 50, 50);

        coBall.setBounds(coBall.getX(), coBall.getY() + 5, 50, 50);
        coCan.setBounds(coCan.getX(), coCan.getY() + 5, 50, 50);
        coChoco.setBounds(coChoco.getX(), coChoco.getY() + 5, 50, 50);
        coGrapes.setBounds(coGrapes.getX(), coGrapes.getY() + 5, 50, 50);
        coMug.setBounds(coMug.getX(), coMug.getY() + 5, 50, 50);
        coSpicy.setBounds(coSpicy.getX(), coSpicy.getY() + 5, 50, 50);

        
        int panelHeight = getHeight();
        if (cfMilk.getY() > panelHeight) {
            cfMilk.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (cfBread.getY() > panelHeight) {
            cfBread.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (cfFish.getY() > panelHeight) {
            cfFish.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (cfCatFood.getY() > panelHeight) {
            cfCatFood.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (cfHotdog.getY() > panelHeight) {
            cfHotdog.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (cfMice.getY() > panelHeight) {
            cfMice.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (coBall.getY() > panelHeight) {
            coBall.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (coCan.getY() > panelHeight) {
            coCan.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (coChoco.getY() > panelHeight) {
            coChoco.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (coGrapes.getY() > panelHeight) {
            coGrapes.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (coMug.getY() > panelHeight) {
            coMug.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
        if (coSpicy.getY() > panelHeight) {
            coSpicy.setBounds((int) (Math.random() * (getWidth() - 100)), -100, 50, 50);
        }
    }

    private void checkCollisions() {
        Rectangle mouthBounds = cat1.getMouthBounds();

        if (mouthBounds.intersects(cfMilk.getBounds())) {
            score += 1;
            cfMilk.setBounds((int) (Math.random() * (getWidth() - 50)), -100, 50, 50);
            cat1.switchToEatingAnimation(); // Show cat2 when colliding with food
            soundPlayer.setSound(3); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(cfBread.getBounds())) {
        	score += 1;
            cfBread.setBounds((int) (Math.random() * (getWidth() - 50)), -100, 50, 50);
            cat1.switchToEatingAnimation();
            soundPlayer.setSound(3); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(cfFish.getBounds())) {
        	score += 1;
            cfFish.setBounds((int) (Math.random() * (getWidth() - 50)), -100, 50, 50);
            cat1.switchToEatingAnimation();
            soundPlayer.setSound(3); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(cfCatFood.getBounds())) {
        	score += 5;
            cfCatFood.setBounds((int) (Math.random() * (getWidth() - 50)), -100, 50, 50);
            cat1.switchToEatingAnimation();
            soundPlayer.setSound(3); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(cfHotdog.getBounds())) {
        	score += 1;
            cfHotdog.setBounds((int) (Math.random() * (getWidth() - 50)), -100, 50, 50);
            cat1.switchToEatingAnimation();
            soundPlayer.setSound(3); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(cfMice.getBounds())) {
        	score += 1;
            cfMice.setBounds((int) (Math.random() * (getWidth() - 50)), -100, 50, 50);
            cat1.switchToEatingAnimation();
            soundPlayer.setSound(3); 
            soundPlayer.playSound();
        }

        // Check collisions with not edible items
        if (mouthBounds.intersects(coBall.getBounds())) {
            score--;
            coBall.setBounds((int) (Math.random() * (getWidth() - 10)), -100, 50, 50);
            cat1.switchToIdleAnimation();
            soundPlayer.setSound(4); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(coCan.getBounds())) {
            score--;
            coCan.setBounds((int) (Math.random() * (getWidth() - 10)), -100, 50, 50);
            cat1.switchToIdleAnimation();
            soundPlayer.setSound(4); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(coChoco.getBounds())) {
        	endGame();
            soundPlayer.setSound(5); 
            soundPlayer.playSound();
        	
            coChoco.setBounds((int) (Math.random() * (getWidth() - 10)), -100, 50, 50);
            cat1.switchToIdleAnimation();
        }
        if (mouthBounds.intersects(coGrapes.getBounds())) {
        	score--;
            coGrapes.setBounds((int) (Math.random() * (getWidth() - 10)), -100, 50, 50);
            cat1.switchToIdleAnimation();
            soundPlayer.setSound(4); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(coMug.getBounds())) {
            score--;
            coMug.setBounds((int) (Math.random() * (getWidth() - 10)), -100, 50, 50);
            cat1.switchToIdleAnimation();
            soundPlayer.setSound(4); 
            soundPlayer.playSound();
        }
        if (mouthBounds.intersects(coSpicy.getBounds())) {
            score--;
            coSpicy.setBounds((int) (Math.random() * (getWidth() - 10)), -100, 50, 50);
            cat1.switchToIdleAnimation();
            soundPlayer.setSound(4); 
            soundPlayer.playSound();
        }


     // Update the score label
        scoreLabel.setText("Score: " + score);

        
        if (score < 0) {
        	endGame();
            
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, 800, 600, null);
    }

}