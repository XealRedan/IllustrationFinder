package com.illustrationfinder.process.image;

/*
 * #%L
 * IllustrationFinder
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


/**
 * Interface implemented by all images processors
 */
public interface IImageProcessor<ImageType, ImageFilter> {
    /**
     * Sets the automatic filter
     * @param automatic <code>true</code> for automatic filtering, <code>false</code> otherwise
     */
    void setAutomaticFilter(boolean automatic);

    /**
     * Sets the filter
     * @param filter the filter to use
     */
    void setFilter(ImageFilter filter);

    /**
     * Process an image
     * @param image the image
     * @return the processed image
     */
    ImageType process(ImageType image);
}
