import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("main: start");

        if (args.length < 3) {
            System.err.println("Wrong arguments! Format: input_path output_path filter_ID (scale ratio)");
            System.exit(-1);
        }
        BufferedImage input = null;
        try {
            input = ImageIO.read(new File(args[0]));
        } catch (IOException e) {
            System.err.println("Error while loading input file");
            e.printStackTrace();
            System.exit(-1);
        }

        BufferedImage out = resize(input, Float.valueOf(args[2]));
        try {
            ImageIO.write(out, args[0].substring(args[0].lastIndexOf(".") + 1), new File(args[1]));
        } catch (Exception e) {
            System.err.println("Error while saving image to " + args[1]);
            e.printStackTrace();
        }

        System.out.println(0);
    }

    public static BufferedImage resize(BufferedImage image, float ratio) {
        return resize(image, Math.round(image.getWidth()*ratio), Math.round(image.getHeight()*ratio));
    }

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage scaled = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = (Graphics2D) scaled.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
        return scaled;
    }
}
