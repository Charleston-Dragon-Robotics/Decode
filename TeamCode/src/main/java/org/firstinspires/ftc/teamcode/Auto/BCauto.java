package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.pedropathing.util.PoseHistory;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.*;
import com.pedropathing.paths.*;
import com.pedropathing.util.*;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Functions;
import org.firstinspires.ftc.teamcode.GamepadStates;
import org.firstinspires.ftc.teamcode.Subassys.Intake;
import org.firstinspires.ftc.teamcode.Subassys.Launcher;
import org.firstinspires.ftc.teamcode.Subassys.Drivetrain;

@Autonomous(name = "BCauto", group = "Autonomous")
public class BCauto extends LinearOpMode {

    private Follower follower; // Pedro Pathing follower

    private final Pose BFStart = new Pose(62.5, -15.5, Math.toRadians(180));
    private final Pose BCStart = new Pose(-52, -51, Math.toRadians(225));
    private final Pose BCScore = new Pose(-15,-15 , Math.toRadians(225));
    private final Pose BCScore2 = new Pose(-15,-15 , Math.toRadians(225));
    private final Pose BFScore = new Pose(53, -10.5, Math.toRadians(210));
    private final Pose B3A = new Pose(-12, -34, Math.toRadians(90));
    private final Pose B3C = new Pose(-12, -51, Math.toRadians(90));
    private final Pose B2A = new Pose(12, -34, Math.toRadians(90));
    private final Pose B2C = new Pose(12, -51, Math.toRadians(90));
    private final Pose B1A = new Pose(36, -34, Math.toRadians(90));
    private final Pose B1C = new Pose(36, -51, Math.toRadians(90));
    private PathChain BscorePreload, Balign3, Bintake3, Bscore3, Balign2, Bintake2, Bscore2, Balign1, Bintake1, Bscore1;


    private Pose currentPose; // Current pose of the robot
    static PoseHistory poseHistory;
    private Timer pathTimer, opModeTimer;

    Launcher Launch = new Launcher();
    org.firstinspires.ftc.teamcode.Subassys.Intake Intake = new Intake();

    private int pathState;

    public enum PathState {
        // START POSITION I GUESS ALSO END POSITION I DONT KNOW WHAT THIS GUY IS TALKING ABOUT
        // DRIVE > MOVEMENT
    }


//    private Path scorePre;
//    private PathChain grab1, score1;

    public void buildPaths() {

        BscorePreload = follower.pathBuilder()
                .addPath(new BezierLine(BCStart, BCScore))
                .setLinearHeadingInterpolation(BCStart.getHeading(), BCScore.getHeading())
                .build();

        Balign3 = follower.pathBuilder()
                .addPath(new BezierLine(BCScore, B3A))
                .setLinearHeadingInterpolation(BCScore.getHeading(), B3A.getHeading())
                .build();

        Bintake3 = follower.pathBuilder()
                .addPath(new BezierLine(B3A, B3C))
                .setLinearHeadingInterpolation(B3A.getHeading(), B3C.getHeading())
                .build();

        Bscore3 = follower.pathBuilder()
                .addPath(new BezierLine(B3C, BCScore))
                .setLinearHeadingInterpolation(B3C.getHeading(), BCScore.getHeading())
                .build();

        Balign2 = follower.pathBuilder()
                .addPath(new BezierLine(BCScore, B2A))
                .setLinearHeadingInterpolation(BCScore.getHeading(), B2A.getHeading())
                .build();

        Bintake2 = follower.pathBuilder()
                .addPath(new BezierLine(B2A, B2C))
                .setLinearHeadingInterpolation(B2A.getHeading(), B2C.getHeading())
                .build();

        Bscore2 = follower.pathBuilder()
                .addPath(new BezierLine(B2C, BFScore))
                .setLinearHeadingInterpolation(B2C.getHeading(), BFScore.getHeading())
                .build();

        Balign1 = follower.pathBuilder()
                .addPath(new BezierLine(BFScore, B1A))
                .setLinearHeadingInterpolation(BCScore.getHeading(), B3A.getHeading())
                .build();

        Bintake1 = follower.pathBuilder()
                .addPath(new BezierLine(B1A, B1C))
                .setLinearHeadingInterpolation(B3A.getHeading(), B3C.getHeading())
                .build();

        Bscore1 = follower.pathBuilder()
                .addPath(new BezierLine(B3C, BFScore))
                .setLinearHeadingInterpolation(B1C.getHeading(), BFScore.getHeading())
                .build();
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        // declare subassembly classes
        Drivetrain Drive = new Drivetrain();

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
        follower.setStartingPose(BCStart);

        pathTimer = new Timer();
        opModeTimer = new Timer();
        opModeTimer.resetTimer();

        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        waitForStart();

        while (opModeIsActive()) {

            follower.update();
            currentPose = follower.getPose();
            buildPaths();
            rFStateMachine();


        }
    }

    private void rFStateMachine() {
        switch (pathState) {
            case 0:
                follower.followPath(BscorePreload, true);
                setPathState(-1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    Launch.autoLaunchFar();
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(Balign3, true);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    Intake.intake(1);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(Bintake3, true);
                    setPathState(5);
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    Intake.stop();
                    setPathState(6);
                }
                break;
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(Bscore3, true);
                    setPathState(7);
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    Launch.autoLaunchFar();
                    setPathState(8);
                }
                break;
            case 8:
                if (!follower.isBusy()) {
                    follower.followPath(Balign2, true);
                    setPathState(9);
                }
                break;
            case 9:
                if (!follower.isBusy()) {
                    Intake.intake(1);
                    setPathState(10);
                }
                break;
            case 10:
                if (!follower.isBusy()) {
                    follower.followPath(Bintake2, true);
                    setPathState(11);
                }
                break;
            case 11:
                if (!follower.isBusy()) {
                    Intake.stop();
                    setPathState(12);
                }
                break;
            case 12:
                if (!follower.isBusy()) {
                    follower.followPath(Bscore2, true);
                    setPathState(13);
                }
                break;
            case 13:
                if (!follower.isBusy()) {
                    Launch.autoLaunchFar();
                    setPathState(14);
                }
                break;
            case 14:
                if (!follower.isBusy()) {
                    follower.followPath(Balign1, true);
                    setPathState(15);
                }
                break;
            case 15:
                if (!follower.isBusy()) {
                    Intake.intake(1);
                    setPathState(16);
                }
                break;
            case 16:
                if (!follower.isBusy()) {
                    follower.followPath(Bintake1, true);
                    setPathState(17);
                }
                break;
            case 17:
                if (!follower.isBusy()) {
                    Intake.stop();
                    setPathState(18);
                }
                break;
            case 18:
                if (!follower.isBusy()) {
                    follower.followPath(Bscore1, true);
                    setPathState(19);
                }
                break;
            case 19:
                if (!follower.isBusy()) {
                    Launch.autoLaunchFar();
                    setPathState(20);
                }
                break;
            case 20:
                if (!follower.isBusy()) {
                    follower.followPath(Balign1, true);
                    setPathState(-1);
                }
                break;
        }
    }
}

