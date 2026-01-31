package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "Auto2", group = "autonomous")

public class Auto2 extends OpMode {

    private Follower follower;
    private Timer pathTimer, actionnTimer, opmodeTimer;
    private int pathState;

    Training Drive = new Training();
    Intake Intake = new Intake();
    Launcher Launch = new Launcher();

    Auto Auto = new Auto();
    //        SensorTraining Sensor = new SensorTraining();
//        ServoTraining Servo = new ServoTraining();
//        Limelight Limelight = new Limelight();
    Functions Fun = new Functions();


    private final Pose BFstart = new Pose(62.5, -15.5, Math.toRadians(180));
    private final Pose BFscore = new Pose(53, -10.5, Math.toRadians(195));
    private final Pose BCstart = new Pose(-24, -24, Math.toRadians(135));
    private final Pose BCscore = new Pose(-24, -24, Math.toRadians(135));
    private final Pose B1a = new Pose(-24, -24, Math.toRadians(135));
    private final Pose B1i = new Pose(-24, -24, Math.toRadians(135));
    private final Pose B2a = new Pose(-24, -24, Math.toRadians(135));
    private final Pose B2i = new Pose(-24, -24, Math.toRadians(135));
    private final Pose B3a = new Pose(-24, -24, Math.toRadians(135));
    private final Pose B3i = new Pose(-24, -24, Math.toRadians(135));

    private Path scorePreload;
    private PathChain BCgrab1;

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    public void intPaths() {

        scorePreload = new Path(new BezierLine(BFstart, BFscore));
        scorePreload.setLinearHeadingInterpolation(BFstart.getHeading(), BFscore.getHeading());

//        BCgrab1 = follower.pathBuilder()
//                .addPath(new BezierLine(BCscore, BFstart))
//                .setLinearHeadingInterpolation(BCscore.getHeading(), BFstart.getHeading())
//                .build();
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(scorePreload);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    Launch.autoLaunch(.5);
                    Drive.sleep(1000);
                    Intake.Feed();
                    Intake.intake(.65);
                    Drive.sleep(100);
                    Intake.stop();
                    Intake.FeedStop();
                    Drive.sleep(400);
                    Intake.Feed();
                    Intake.intake(.65);
                    Drive.sleep(175);
                    Intake.stop();
                    Intake.FeedStop();
                    Drive.sleep(400);
                    Intake.Feed();
                    Intake.intake(.65);
                    Drive.sleep(220);
                    Intake.stop();
                    Intake.FeedStop();
                    Launch.stop();
                    Drive.sleep(500);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(BCgrab1, true);
                    setPathState(2);
                }
                break;
            case 3:
                break;
        }


    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
    }

    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        follower = Constants.createFollower(hardwareMap);
        intPaths();
        follower.setStartingPose(BFstart);

        Drive.init(Auto);
        Launch.init(Auto);
        Intake.init(Auto);

        // names subassembly classes


    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }
}
