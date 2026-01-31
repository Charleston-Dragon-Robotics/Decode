package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.pedropathing.util.PoseHistory;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.*;
import com.pedropathing.paths.*;
import com.pedropathing.util.*;
//import org.firstinspires.ftc.teamcode.Limelight;


@Autonomous(name = "Auto", group = "Autonomous")
public class Auto extends LinearOpMode {

    private final Pose BFScore = new Pose(53, -10.5, Math.toRadians(195));
    private final Pose BFStart = new Pose(62.5, -15.5, Math.toRadians(180));
    private final Pose B1A = new Pose(36, -30, Math.toRadians(270));

    private PathChain scorePreload;
    private PathChain align1;
    private PathChain intake1;

    private Pose currentPose; // Current pose of the robot
    private Follower follower; // Pedro Pathing follower
    static PoseHistory poseHistory;
    private Timer pathTimer, opModeTimer;

    Launcher Launch = new Launcher();

    private int pathState;

    public enum PathState {
        // START POSITION I GUESS ALSO END POSITION I DONT KNOW WHAT THIS GUY IS TALKING ABOUT
        // DRIVE > MOVEMENT
    }


//    private Path scorePre;
//    private PathChain grab1, score1;

    public void buildPaths() {
//        scorePreload = new PathChain(new BezierLine(BFStart, BFScore));
//        scorePre.setLinearHeadingInterpolation(start.getHeading(), score.getHeading());

        scorePreload = follower.pathBuilder()
                .addPath(new BezierLine(BFStart, BFScore))
                .setLinearHeadingInterpolation(BFStart.getHeading(), BFScore.getHeading())
                .build();

        align1 = follower.pathBuilder()
                .addPath(new BezierLine(BFScore, B1A))
                .setLinearHeadingInterpolation(BFScore.getHeading(), B1A.getHeading())
                .build();
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

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(BFStart);

        pathTimer = new Timer();
        opModeTimer = new Timer();
        opModeTimer.resetTimer();

//        follower.setStartingPose(new Pose());
//
//        poseHistory = follower.getPoseHistory();

//        boolean red = false;
//        boolean done = false;

        telemetry.addLine("press B if red alliance");
        telemetry.update();

        GamepadStates newGamePad2 = new GamepadStates(gamepad2);


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
            currentPose = follower.getPose();

            buildPaths();

            updateStateMachine();
//            stateSelect();


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

    public void updateStateMachine() {
        switch (pathState) {
            case 0:
                follower.followPath(scorePreload, true);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    Launch.autoLaunch(0.5);
                    sleep(2000);
                    Launch.stop();
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(align1, true);
                    setPathState(-1);
                }
                break;
        }
    }

//    public void stateSelect() {
//        switch (pathState) {
//            case 0:
//                follower.followPath(scorePre, true);
//                setPathState(1);
//                break;
//            case 1:
//                if (!follower.isBusy()) {
//                    Launch.autoLaunch(0.5);
//                    sleep(2000);
//                    Launch.stop();
//                    setPathState(-1);
//                }
//                break;
//        }
//    }
}
