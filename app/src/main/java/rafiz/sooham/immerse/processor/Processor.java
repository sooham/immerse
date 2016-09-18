package rafiz.sooham.immerse.processor;
import rafiz.sooham.immerse.callbacks.ProcessorCallback;
import rafiz.sooham.immerse.datatypes.Position;

import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;

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

    private static ProcessorCallback callback;

    static{System.loadLibrary("opencv_java3"); }

    public Processor() {
        mcamera = Camera.open();
    }

    public static void setCallback(ProcessorCallback callback){
        Processor.callback = callback;
    }

    /**
     *
     * @param filename name of image file to open
     * @return list of Matrices corresponding to transformed planes extracted on image.
     */
    public static List<Mat> getSpideryMesh(String filename, Position root, Position topL, Position topR, Position botL, Position botR) {

        Point vanishing_pt = new Point(root.x, root.y);
        Point[] inner_rect_pts = new Point[4];
        inner_rect_pts[0] = new Point(topL.x, topL.y);
        inner_rect_pts[1] = new Point(topR.x, topR.y);
        inner_rect_pts[2] = new Point(botL.x, botL.y);
        inner_rect_pts[3] = new Point(botR.x, botR.y);

        Log.d(TASK, "in getSpideryMesh");
        Log.d(TASK, "vanishing point = " + vanishing_pt);

        if (inner_rect_pts.length != 4) {
            throw new RuntimeException("Point array needs 4 points");
        }

        Mat input = Imgcodecs.imread(filename);

        Log.d(TASK, "Input size: " + input.size().toString());
        // calculate the other points
        // outer rect top_left, top_right, bottom_right, bottom_left
        double width = inner_rect_pts[0].x - inner_rect_pts[2].x;
        double height = inner_rect_pts[0].y - inner_rect_pts[2].y;

        Size image_sz = input.size();
        double multiplier;

        if (width/image_sz.width > height/image_sz.height) {
            Log.d(TASK, "Expand to full width");
            multiplier = (image_sz.width / width);
            height = height * multiplier;
            width = image_sz.width;
        } else {
            Log.d(TASK, "Expand to full height");
            multiplier = (image_sz.width / width);
            width = width * multiplier;
            height = image_sz.height;
        }

        Log.d(TASK, "multiplier: " + multiplier);

        Point[] outer_rect_pts = new Point[4];

        for (int i = 0; i < outer_rect_pts.length; i++) {
            outer_rect_pts[i] = new Point((inner_rect_pts[i].x - vanishing_pt.x) * multiplier + vanishing_pt.x, (inner_rect_pts[i].y - vanishing_pt.y) * multiplier + vanishing_pt.y);
            Log.d(TASK, "outer_rect_pts[" + i +"] = "  + outer_rect_pts);

        }
        callback.call();
        return null;
        // return perspectiveTransformPlanes();

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