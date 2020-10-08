package testFramework.helpers;

import testFramework.Context;
import testFramework.actors.Actor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Miscellaneous {
    public static boolean urlPointsToAnImage(String url) {
        try {
            BufferedImage img = ImageIO.read(new URL(url));
            return true;
        } catch (IllegalArgumentException | IOException exception) {
            Actor.writeToHtmlReport(exception.getMessage() + " raised when checking url :" + url + ":");
        }
        return false;
    }
}
