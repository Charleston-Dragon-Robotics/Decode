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

@Autonomous(name = "RCauto", group = "Autonomous")
public class RCauto extends LinearOpMode {

    private Follower follower; // Pedro Pathing follower
    private final Pose RCStart = new Pose(-52, 51, Math.toRadians(225));
    private final Pose RFStart = new Pose(62.5, 15.5, Math.toRadians(180));
    private final Pose RFScore = new Pose(53, 10.5, Math.toRadians(215));
    private final Pose RFScore2 = new Pose(60, 5.5, Math.toRadians(215));
    private final Pose RCScore = new Pose(-15,15 , Math.toRadians(225));
    private final Pose R3A = new Pose(-12, 34, Math.toRadians(90));
    private final Pose R3C = new Pose(-12, 51, Math.toRadians(90));
    private final Pose R2A = new Pose(12, 34, Math.toRadians(90));
    private final Pose R2C = new Pose(12, 51, Math.toRadians(90));
    private final Pose R1A = new Pose(36, 34, Math.toRadians(90));
    private final Pose R1C = new Pose(36, 51, Math.toRadians(90));
    private PathChain RscorePreload, Ralign1, Rintake1, Rscore1, Ralign3, Rintake3, Rscore3, Ralign2, Rintake2, Rscore2;

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

        RscorePreload = follower.pathBuilder()
                .addPath(new BezierLine(RCStart, RCScore))
                .setLinearHeadingInterpolation(RCStart.getHeading(), RCScore.getHeading())
                .build();

        Ralign1 = follower.pathBuilder()
                .addPath(new BezierLine(RFScore, R1A))
                .setLinearHeadingInterpolation(RFScore.getHeading(), R1A.getHeading())
                .build();

        Rintake1 = follower.pathBuilder()
                .addPath(new BezierLine(R1A, R1C))
                .setLinearHeadingInterpolation(R1A.getHeading(), R1C.getHeading())
                .build();

        Rscore1 = follower.pathBuilder()
                .addPath(new BezierLine(R1C, RFScore2))
                .setLinearHeadingInterpolation(R1C.getHeading(), RFScore2.getHeading())
                .build();
        Ralign2 = follower.pathBuilder()
                .addPath(new BezierLine(RFScore, R2A))
                .setLinearHeadingInterpolation(RFScore.getHeading(), R1A.getHeading())
                .build();

        Rintake2 = follower.pathBuilder()
                .addPath(new BezierLine(R2A, R2C))
                .setLinearHeadingInterpolation(R1A.getHeading(), R1C.getHeading())
                .build();

        Rscore2 = follower.pathBuilder()
                .addPath(new BezierLine(R2C, RCScore))
                .setLinearHeadingInterpolation(R1C.getHeading(), RFScore2.getHeading())
                .build();
        Ralign3 = follower.pathBuilder()
                .addPath(new BezierLine(RCScore, R3A))
                .setLinearHeadingInterpolation(RFScore.getHeading(), R1A.getHeading())
                .build();

        Rintake3 = follower.pathBuilder()
                .addPath(new BezierLine(R3A, R3C))
                .setLinearHeadingInterpolation(R1A.getHeading(), R1C.getHeading())
                .build();

        Rscore3 = follower.pathBuilder()
                .addPath(new BezierLine(R3C, RFScore2))
                .setLinearHeadingInterpolation(R1C.getHeading(), RFScore2.getHeading())
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
        follower.setStartingPose(RCStart);

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
                follower.followPath(RscorePreload, true);
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
                    follower.followPath(Ralign3, true);
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
                    follower.followPath(Rintake3, true);
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
                    follower.followPath(Rscore3, true);
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
                    follower.followPath(Ralign2, true);
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
                    follower.followPath(Rintake2, true);
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
                    follower.followPath(Rscore2, true);
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
                    follower.followPath(Ralign1, true);
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
                    follower.followPath(Rintake1, true);
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
                    follower.followPath(Rscore1, true);
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
                    follower.followPath(Ralign1, true);
                    setPathState(-1);
                }
                break;

        }
    }
}
