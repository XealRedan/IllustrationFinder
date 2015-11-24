package com.illustrationfinder;

import com.illustrationfinder.process.image.BufferedImageProcessor;
import com.illustrationfinder.process.image.IImageProcessor;
import com.illustrationfinder.process.post.HtmlPostProcessor;
import com.illustrationfinder.process.post.IPostProcessor;
import com.illustrationfinder.process.searchengine.google.GoogleSearchEngine;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.validator.UrlValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Illustration finder controller
 */
@Controller
@RequestMapping("/IllustrationFinder")
public class IllustrationFinderController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showIllustrationFinder() {
        final ModelAndView modelAndView = new ModelAndView("/index");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"url", "preferred-width", "preferred-height"})
    public ModelAndView showIllustrationFinderResults(
            ModelMap modelMap,
            @RequestParam(value = "url") String pUrl,
            @RequestParam(value = "preferred-width") String pPreferredWidth,
            @RequestParam(value = "preferred-height") String pPreferredHeight) {
        final ModelAndView modelAndView = new ModelAndView("/index");

        // Add the URL to attributes
        modelMap.addAttribute("pUrl", pUrl);

        // Check if the URL is valid
        boolean isUrlValid = false;

        String url = pUrl;
        if(url != null) {
            url = StringEscapeUtils.escapeHtml4(url);

            if(new UrlValidator(new String[]{"http","https"}).isValid(url)) {
                isUrlValid = true;
            }
        }

        modelMap.addAttribute("isUrlValid", true);

        // Get the images
        try {
            if(isUrlValid) {
                final IPostProcessor postProcessor = new HtmlPostProcessor();
                final GoogleSearchEngine searchEngine = new GoogleSearchEngine();
                final IImageProcessor<BufferedImage, BufferedImageOp> imageProcessor = new BufferedImageProcessor();

                imageProcessor.setPreferredSize(new Dimension(Integer.parseInt(pPreferredWidth), Integer.parseInt(pPreferredHeight)));

                final IllustrationFinder illustrationFinder = new IllustrationFinder();
                illustrationFinder.setPostProcessor(postProcessor);
                illustrationFinder.setSearchEngine(searchEngine);
                illustrationFinder.setImageProcessor(imageProcessor);

                final List<BufferedImage> images = illustrationFinder.getImages(new URL(pUrl));

                // Convert images to base64 strings
                final List<String> imagesAsStrings = new ArrayList<>();

                if(images != null) {
                    for (BufferedImage image : images) {
                        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        try {
                            ImageIO.write(image, "png", baos);
                            baos.flush();
                            final byte[] imageInByteArray = baos.toByteArray();
                            baos.close();
                            final String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

                            imagesAsStrings.add(b64);
                        } catch (IOException e) {
                            // Failed to convert the image
                        }
                    }
                }

                modelMap.addAttribute("images", imagesAsStrings);
            }
        } catch (IOException e) {
            // Exception triggered if the URL is malformed, it should not happen because the URL is validated before
        }

        return modelAndView;
    }
}
