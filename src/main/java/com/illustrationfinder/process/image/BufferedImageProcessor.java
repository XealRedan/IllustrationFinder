package com.illustrationfinder.process.image;

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
            final Image tmp = image.getScaledInstance((int)this.preferredSize.getWidth(), (int)this.preferredSize.getHeight(), Image.SCALE_SMOOTH);
            source = new BufferedImage((int)this.preferredSize.getWidth(), (int)this.preferredSize.getHeight(), BufferedImage.TYPE_INT_ARGB);

            final Graphics2D g2d = source.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
        } else {
            source = image;
        }

        // Apply a filter on the image
        if(this.filter == null && !this.automaticFilter)
            return source;

        final BufferedImage dest = this.filter.createCompatibleDestImage(source, ColorModel.getRGBdefault());

        if(this.automaticFilter == false) {
            this.filter.filter(source, dest);
        } else {
            this.selectFilter(source).filter(source, dest);
        }

        return dest;
    }

    private BufferedImageOp selectFilter(BufferedImage source) {
        final List<BufferedImageOp> filters = new ArrayList<>();

        filters.add(new KaleidoscopeFilter());
        filters.add(new DiffuseFilter());
        filters.add(new PinchFilter());
        filters.add(new InvertFilter());
        filters.add(new ContrastFilter());

        final int selectedFilter = (int)Math.round(Math.random() * (filters.size() - 1));

        return filters.get(selectedFilter);
    }
}
