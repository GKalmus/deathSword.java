import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.MotionBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class JavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage peaLava) {

        Group juur = new Group();
        Circle ring = new Circle(0, 0, 30, Color.GREEN);
        juur.getChildren().add(ring);


        Scene stseen1 = new Scene(juur,600,600);
        peaLava.setTitle("Ring");
        peaLava.setScene(stseen1);
        peaLava.show();

        Button nupp1 = new Button("Olen nõus");
        nupp1.setLayoutX(100);
        nupp1.setLayoutY(60);
        MotionBlur udustamine = new MotionBlur(); //proovin teist efekti
        nupp1.setEffect(udustamine);
        juur.getChildren().add(nupp1); // nupp lisatakse juure alluvaks

        FadeTransition ft = new FadeTransition(Duration.millis(4000), ring);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        //ft.play();

        Path path = new Path(); //ei saanud aru kuidas liigutada ruudus
        path.getElements().add(new MoveTo(300,300));
        path.getElements().add(new CubicCurveTo(20, 80, 240, 0, 280, 80));

        PathTransition pt = new PathTransition();
        pt.setNode(ring);
        pt.setPath(path);
        pt.setDuration(Duration.seconds(5));
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(1);
        pt.setAutoReverse(true);
        //pt.play();
        SequentialTransition seqT = new SequentialTransition(pt,ft);
        seqT.play();


    }
}
