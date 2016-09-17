package rafiz.sooham.immerse.processor;

import android.hardware.Camera;
import android.os.Environment;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Processor {

    /**
     *
     * @param filename
     * @param vanishing_pt
     * @param inner_rect_pts [top_left, top_right, bottom_right, bottom_left]
     * @return
     */
    public static final String TASK = "Processor";
    private static Camera mcamera;

    public Processor() {
        mcamera = Camera.open();
    }


    public static List<Mat> getSpideryMesh(String filename, Point vanishing_pt, Point[] inner_rect_pts) {

        if (inner_rect_pts.length != 4) {
            throw new RuntimeException("Point array needs 4 points");
        }

        Mat input = Imgcodecs.imread(filename);
        // calculate the other points
        // outer rect top_left, top_right, bottom_right, bottom_left
        double width = inner_rect_pts[0].x - inner_rect_pts[2].x;
        double height = inner_rect_pts[0].y - inner_rect_pts[2].y;

        Size image_sz = input.size();
        double multiplier;

        if (width/image_sz.width > height/image_sz.height) {
            multiplier = (image_sz.width / width)
            height = height * multiplier;
            width = image_sz.width;
        } else {
            multiplier = (image_sz.width / width)
            width = width * multiplier;
            height = image_sz.height;
        }

        Point[] outer_rect_pts = new Point[4];

        for (int i = 0; i < outer_rect_pts.length; i++) {
            outer_rect_pts[i] = new Point((inner_rect_pts[i].x - vanishing_pt.x) * multiplier + vanishing_pt.x, (inner_rect_pts[i].y - vanishing_pt.y) * multiplier + vanishing_pt.y);
        }

        // return perspectiveTransformPlanes();
        return null;
        // getPerspectiveTransform
        // void perspectiveTransform(InputArray src, OutputArray dst, InputArray m)
    }

    public static List<Mat> perspectiveTransformPlanes(Point vanishing_pt, Point[] inner_rect_pts, Point[] outer_rect_pts) {
        return null;
    }

    // Return focal distance in millimeters
    public static double getFocalDistance() {
        Camera.Parameters cameraParams = mcamera.getParameters();
        return cameraParams.getFocalLength();
    }
}
