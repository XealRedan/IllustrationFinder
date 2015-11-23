package com.illustrationfinder.process.image.filters;

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
