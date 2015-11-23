package com.illustrationfinder.process.image.filters;

import com.jhlabs.image.EdgeFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.InvertFilter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;

/**
 * Created by alombard on 23/11/2015.
 */
public class InvertedEdgeFilter extends EdgeFilter {
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        BufferedImage img = super.filter(src, dest);

        img = new GrayscaleFilter().filter(img, dest);
        img = new InvertFilter().filter(img, dest);

        return img;
    }
}
