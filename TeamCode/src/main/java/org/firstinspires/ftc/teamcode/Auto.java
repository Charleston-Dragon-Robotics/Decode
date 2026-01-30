package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.media.metrics.PlaybackErrorEvent;

import com.pedropathing.ftc.InvertedFTCCoordinates;
import com.pedropathing.ftc.PoseConverter;
import com.pedropathing.paths.callbacks.PoseCallback;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


import com.pedropathing.util.PoseHistory;
import com.pedropathing.follower.Follower;

import com.bylazar.configurables.PanelsConfigurables;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.bylazar.field.FieldManager;
import com.bylazar.field.PanelsField;
import com.bylazar.field.Style;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.*;
import com.pedropathing.math.*;
import com.pedropathing.paths.*;
import com.pedropathing.telemetry.SelectableOpMode;
import com.pedropathing.util.*;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.source.tree.IfTree;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Training;
import org.firstinspires.ftc.teamcode.GamepadStates;
import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.Outtake;
import org.firstinspires.ftc.teamcode.SensorTraining;
import org.firstinspires.ftc.teamcode.ServoTraining;
//import org.firstinspires.ftc.teamcode.Limelight;
import org.firstinspires.ftc.teamcode.Functions;

@Autonomous(name = "Auto", group = "Autonomous")
public class Auto extends LinearOpMode {

    public static Follower follower;
    static PoseHistory poseHistory;
    private Timer pathTimer, opModeTimer;

    private int pathState;

    public enum PathState {
        // START POSITION I GUESS ALSO END POSITION I DONT KNOW WHAT THIS GUY IS TALKING ABOUT
        // DRIVE > MOVEMENT
    }

    private final Pose start = new Pose(62, -15, Math.toRadians(180));
    private final Pose score = new Pose(-48, -48, Math.toRadians(135));

    private Path scorePre;
    private PathChain grab1, score1;

    public void buildPaths() {
        scorePre = new Path(new BezierLine(start, score));
        scorePre.setLinearHeadingInterpolation(start.getHeading(), score.getHeading());

        grab1 = follower.pathBuilder()
                .addPath(new BezierLine(score, start))
                .setLinearHeadingInterpolation(score.getHeading(), start.getHeading())
                .build();
    }

    public void stateSelect() {
        switch (pathState) {
            case 0:
                follower.followPath(scorePre, true);
                if (!follower.isBusy()) {
                    setPathState(1);
                }
                break;
            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(grab1);
                }
                if (!follower.isBusy()) {
                    setPathState(2);
                }
                break;
        }
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        // declare subassembly classes
        Training Drive = new Training();
        Intake Intake = new Intake();
        Launcher Launch = new Launcher();
//        SensorTraining Sensor = new SensorTraining();
//        ServoTraining Servo = new ServoTraining();
//        Limelight Limelight = new Limelight();
        Functions Fun = new Functions();

        // names subassembly classes
        Drive.init(this);
        Intake.init(this);
//        Outtake.init(this);
//        Servo.init(this);
//        Limelight.init(this);
        Launch.init(this);
        Fun.init(this);

        pathTimer = new Timer();
        opModeTimer = new Timer();
        opModeTimer.resetTimer();
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(start);


//        follower.setStartingPose(new Pose());
//
//        poseHistory = follower.getPoseHistory();

//        boolean red = false;
//        boolean done = false;

        telemetry.addLine("press B if red alliance");
        telemetry.update();

        GamepadStates newGamPad2 = new GamepadStates(gamepad2);


//        while(!done) {
//
//            newGamPad2.updateState();
//
//            if (newGamPad2.b.released) {
//                red = true;
//            }
//
//            if(newGamPad2.a.released) {
//                done  = true;
//            }
//        }

        //code for the auto phase
        waitForStart();


//
//        Fun.forwardForDist(12, .5);

        while (opModeIsActive()) {
            follower.update();
            stateSelect();


//            while (follower.isBusy()){
//                    telemetry.addLine("follower is busy");
//                follower.followPath(scorePre);
//            }
//            break;


//            setPathState();
//            telemetry.addLine("Path built");
//            telemetry.update();
//            follower.followPath(scorePre);
//            telemetry.addLine("Path followed");
//            telemetry.update();

//        Drive.forward(1);
//        sleep(500);
//        Drive.stop();
//            Drive.backwards(.8);
//            sleep(1200);
//            Drive.stop();
//            sleep(500);

//            Limelight.turn2LL();
//            if (red==true){
//                Drive.left(.65);
//                sleep(500);
//            }
//            else{
//                Drive.right(.65);
//                sleep(500);
//            }
//            Drive.stop();
//            sleep(500);
////            Limelight.detectPattern();
//            sleep(1000);
//
//            if (red==true){
//                Drive.right(.65);
//                sleep(500);
//            }
//            else{
//                Drive.left(.65);
//                sleep(500);
//            }
//            Drive.stop();
//            Launch.autoLaunch(.5);
//            sleep(1000);
//            Intake.Feed();
//            Intake.intake(.65);
//            sleep(100);
//            Intake.stop();
//            Intake.FeedStop();
//            sleep(400);
//            Intake.Feed();
//            Intake.intake(.65);
//            sleep(175);
//            Intake.stop();
//            Intake.FeedStop();
//            sleep(400);
//            Intake.Feed();
//            Intake.intake(.65);
//            sleep(220);
//            Intake.stop();
//            Intake.FeedStop();
//            Launch.stop();
//            sleep(500);
//            if(red==true) {
////                Drive.StraffRight(.5);
////                sleep(1000);
////                Drive.stop();
//            } else if (red==false) {
////                Drive.StraffLeft(.5);
////                sleep(1000);
////                Drive.stop();
//            }
//            break;
        }
    }
}
