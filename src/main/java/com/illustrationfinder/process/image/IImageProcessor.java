package com.illustrationfinder.process.image;

/**
 * Created by alombard on 19/11/2015.
 * Interface implemented by all images processors
 */
public interface IImageProcessor<ImageType> {
    ImageType process(ImageType image);
}
