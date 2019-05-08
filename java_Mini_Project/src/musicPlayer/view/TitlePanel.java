package musicPlayer.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {
    private BufferedImage image;
    public TitlePanel(){
        try {
            File file = new File("C:\\Temp\\titlePic.png");
            image = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1900, 200);
        
    }
    public BufferedImage getImage() {
        return image;
    }
    
}