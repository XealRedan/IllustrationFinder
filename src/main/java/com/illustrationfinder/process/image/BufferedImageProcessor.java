package com.illustrationfinder.process.image;

/*
 * #%L
 * Illustration finder
 * %%
 * Copyright (C) 2015 Alexandre Lombard
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.illustrationfinder.process.image.filters.InvertedEdgeFilter;
import com.jhlabs.image.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Process an image
 */
public class BufferedImageProcessor implements IImageProcessor<BufferedImage, BufferedImageOp> {

    private BufferedImageOp filter;
    private Dimension preferredSize;

    private boolean automaticFilter = true;

    public BufferedImageOp getFilter() {
        return filter;
    }

    public void setFilter(BufferedImageOp filter) {
        this.filter = filter;
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    public boolean isAutomaticFilter() {
        return automaticFilter;
    }

    @Override
    public void setAutomaticFilter(boolean automaticFilter) {
        this.automaticFilter = automaticFilter;
    }

    @Override
    public BufferedImage process(BufferedImage image) {
        final BufferedImage source;

        // Resize the image to the preferred size if defined
        if(this.preferredSize != null) {
            // Detect the source ratio
            final double ratio = ((double)image.getWidth()) / image.getHeight();

            // Rescale the image keeping the ratio
            final double targetRatio = this.preferredSize.getWidth() / this.preferredSize.getHeight();

            final double toleranceMargin = 0.20;

            final GaussianFilter gaussianFilter = new GaussianFilter();
            gaussianFilter.setRadius(10);

            // TODO Add a tolerance margin
            if(targetRatio > ratio + (1d + toleranceMargin)) {
                // If the target image is wider (in proportion) than the source image
                final Image centered = image.getScaledInstance((int) (image.getWidth() * this.preferredSize.getHeight() / image.getHeight()), (int)this.preferredSize.getHeight(), Image.SCALE_SMOOTH);
                source = new BufferedImage((int)this.preferredSize.getWidth(), (int)this.preferredSize.getHeight(), BufferedImage.TYPE_INT_ARGB);

                final Image zoomed = image.getScaledInstance((int)this.preferredSize.getWidth(), (int) (image.getHeight() * this.preferredSize.getWidth() / image.getWidth()), Image.SCALE_SMOOTH);

                final Graphics2D g2d = source.createGraphics();
                g2d.drawImage(getBufferedImage(zoomed), gaussianFilter, 0, -(int)((zoomed.getHeight(null) - this.preferredSize.getHeight()) / 2d));
//                g2d.drawImage(centered, (int)((this.preferredSize.getWidth() - centered.getWidth(null)) / 2d), 0, null);
                g2d.drawImage(getBufferedImage(centered), new ShadowFilter(), (int)((this.preferredSize.getWidth() - centered.getWidth(null)) / 2d), 0);
                g2d.dispose();
            } else if(targetRatio < ratio * (1d - toleranceMargin)) {
                // If the target image is less wide (in proportion) than the source image
                final Image centered = image.getScaledInstance((int)this.preferredSize.getWidth(), (int) (image.getHeight() * this.preferredSize.getWidth() / image.getWidth()), Image.SCALE_SMOOTH);
                source = new BufferedImage((int)this.preferredSize.getWidth(), (int)this.preferredSize.getHeight(), BufferedImage.TYPE_INT_ARGB);

                final Image zoomed = image.getScaledInstance((int) (image.getWidth() * this.preferredSize.getHeight() / image.getHeight()), (int)this.preferredSize.getHeight(), Image.SCALE_SMOOTH);

                final Graphics2D g2d = source.createGraphics();
                g2d.drawImage(getBufferedImage(zoomed), gaussianFilter, -(int)((zoomed.getWidth(null) - this.preferredSize.getWidth()) / 2d), 0);
//                g2d.drawImage(centered, 0, (int)((this.preferredSize.getHeight() - centered.getHeight(null)) / 2d), null);
                g2d.drawImage(getBufferedImage(centered), new ShadowFilter(), 0, (int)((this.preferredSize.getHeight() - centered.getHeight(null)) / 2d));
                g2d.dispose();
            } else {
                // Rescale ignoring source ratio
                final Image tmp = image.getScaledInstance((int)this.preferredSize.getWidth(), (int)this.preferredSize.getHeight(), Image.SCALE_SMOOTH);
                source = new BufferedImage((int)this.preferredSize.getWidth(), (int)this.preferredSize.getHeight(), BufferedImage.TYPE_INT_ARGB);

                final Graphics2D g2d = source.createGraphics();
                g2d.drawImage(tmp, 0, 0, null);
                g2d.dispose();
            }


        } else {
            source = image;
        }

        // Apply a filter on the image
        if(this.filter == null && !this.automaticFilter)
            return source;

        if(this.automaticFilter == false) {
            final BufferedImage dest = this.filter.createCompatibleDestImage(source, ColorModel.getRGBdefault());
            this.filter.filter(source, dest);

            return dest;
        } else {
            final BufferedImageOp selectedFilter = this.selectFilter(source);
            final BufferedImage dest = selectedFilter.createCompatibleDestImage(source, ColorModel.getRGBdefault());
            selectedFilter.filter(source, dest);

            return dest;
        }
    }

    private BufferedImageOp selectFilter(BufferedImage source) {
        final List<BufferedImageOp> filters = new ArrayList<>();

        final KaleidoscopeFilter kaleidoscopeFilter = new KaleidoscopeFilter();
        kaleidoscopeFilter.setSides(5);

        filters.add(kaleidoscopeFilter);
        filters.add(new DiffuseFilter());
//        filters.add(new PinchFilter());
        filters.add(new InvertFilter());
        filters.add(new InvertedEdgeFilter());
        filters.add(new ContrastFilter());
        filters.add(new ContrastFilter());


        final int selectedFilter = (int)Math.round(Math.random() * (filters.size() - 1));

        return filters.get(selectedFilter);
    }

    private static BufferedImage getBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
