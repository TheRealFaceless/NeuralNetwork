package dev.faceless.core.trainingdata;

import dev.faceless.core.MLData;
import dev.faceless.core.MLDataSet;
import dev.faceless.core.logger.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageDataSet extends MLDataSet {

    public ImageDataSet(String path) {
        super();
        init(path);
    }

    private void init(String path) {
        File rootDir = new File(path);
        if(!rootDir.isDirectory()) throw new IllegalStateException("File must be a directory containing directories of images labelled appropriately");
        File[] labelDirs = rootDir.listFiles();
        if(labelDirs == null) return;

        for(File labelDir : labelDirs) {
            if (!labelDir.isDirectory()) continue;

            File[] imageFiles = labelDir.listFiles();
            if (imageFiles == null) continue;

            for (File imageFile : imageFiles) {
                BufferedImage image = loadImage(imageFile);
                if(image == null) throw new RuntimeException("Image was null while attempting to create data");
                double[] inputs = getBinaryValue(image);
                double[] targets = getTarget(Integer.parseInt(labelDir.getName()));
                MLData data = new MLData(inputs, targets);
                addMLData(data);
                Logger.getLogger().logInfo(String.format(
                        "Created and added data from directory: %s, file: %s%n[Input: %s]%n[Output: %s]",
                        labelDir.getName(), imageFile.getName(), Arrays.toString(inputs), Arrays.toString(targets)
                ));            }
        }
    }

    public static BufferedImage loadImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            Logger.getLogger().logError(("Error loading image: " + e.getMessage()));
            return null;
        }
    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            Logger.getLogger().logError(("Error loading image: " + e.getMessage()));
            return null;
        }
    }

    public static double[] getBinaryValue(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        final List<Double> binaryList = new ArrayList<>();

        for(int x = 0; x < height; x++) {
            for(int y = 0; y < width; y++) {
                int rgb = image.getRGB(x, y);
                binaryList.add((rgb < 0) ? 0.5 : ((rgb == 0) ? 0 : 1));
            }
        }
        double[] binaryArray = new double[binaryList.size()];
        for (int i = 0; i < binaryList.size(); i++) {
            binaryArray[i] = binaryList.get(i);
        }
        return binaryArray;
    }

    public static double[] getTarget(int num) {
        return switch (num) {
            case 0 -> new double[]{0, 0, 0, 0, 0, 0, 0, 0};
            case 1 -> new double[]{0, 0, 0, 0, 0, 0, 0, 1};
            case 2 -> new double[]{0, 0, 0, 0, 0, 0, 1, 0};
            case 3 -> new double[]{0, 0, 0, 0, 0, 0, 1, 1};
            case 4 -> new double[]{0, 0, 0, 0, 0, 1, 0, 0};
            case 5 -> new double[]{0, 0, 0, 0, 0, 1, 0, 1};
            case 6 -> new double[]{0, 0, 0, 0, 0, 1, 1, 0};
            case 7 -> new double[]{0, 0, 0, 0, 0, 1, 1, 1};
            case 8 -> new double[]{0, 0, 0, 0, 1, 0, 0, 0};
            case 9 -> new double[]{0, 0, 0, 0, 1, 0, 0, 1};
            default -> new double[0];
        };
    }
}
