/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.utility;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;
import javax.imageio.ImageIO;

/**
 *
 * @author sorena
 */
public class AppUtil {

    /**
     *
     * @return new UUID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String encode64(byte[] bytes) {
        return Base64.encode(bytes);
    }

    public static byte[] decode64(String text) {
        return Base64.decode(text);
    }

    public static boolean saveImage(byte[] b, String imageType, String imageName) {
        try {
            InputStream in = new ByteArrayInputStream(b);
            BufferedImage image = ImageIO.read(in);
            ImageIO.write(image, "jpg", new File("/home/sorena/Documents/Projects/Faradeh/Web-Server/Write/src/main/webapp/content/image/" + imageType + "/"
                    + imageName + ".jpg"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
