package org.firstinspires.ftc.teamcode.TeleOp;

import android.app.Activity;
import android.icu.util.ICUUncheckedIOException;
import android.view.View;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.PoseHistory;
import com.pedropathing.util.Timer;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Functions;
import org.firstinspires.ftc.teamcode.GamepadStates;
import org.firstinspires.ftc.teamcode.Subassys.Drivetrain;
import org.firstinspires.ftc.teamcode.Subassys.Intake;
import org.firstinspires.ftc.teamcode.Subassys.Launcher;
//import com.bylazar.


@TeleOp(name = "Teleop", group = "Teleop")
// this is the thing that we run
public class teleop extends LinearOpMode {

    private Follower follower;
    private Pose LockDown1 = new Pose(0, 0);
    private Pose LockDown2 = new Pose(0, 0.0001);

    private PathChain LockDown;

    private Pose currentPose; // Current pose of the robot
    static PoseHistory poseHistory;
    private Timer pathTimer, opModeTimer;
    View relativeLayout;

    private Limelight3A limelight;

    private int Pathstate;

    private double x;
    private double y;

    public void buildPaths() {
        LockDown = follower.pathBuilder()
                .addPath(new BezierLine(LockDown1, LockDown2))
                .setLinearHeadingInterpolation(LockDown1.getHeading(), LockDown1.getHeading())
                .build();
    }

    public void setPathState(int pState) {
        Pathstate = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        // declare subassembly classes
        Drivetrain Train = new Drivetrain();
        Launcher Launcher = new Launcher();
        Intake Intake = new Intake();
        Functions Fun = new Functions();

        // Is this still/will be needed?
//        ServoTraining Servo = new ServoTraining();

////        Limelight LL = new Limelight();
//        Color Color = new Color();

        // initialize subassembly classes
        Train.init(this);
        Intake.init(this);
        Launcher.init(this);
        Fun.init(this);
//        Servo.init(this);
//        LL.init(this);
//        Color.init(this);

        //List fiducialResult;

        //limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        telemetry.setMsTransmissionInterval(11);
//        limelight.pipelineSwitch(7);
//        limelight.start();

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

//        LL.getStatus();

        double speed = 0.7;

        waitForStart();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(LockDown1);

        follower.update();
        currentPose = follower.getPose();
        buildPaths();

        while (opModeIsActive()) {

            newGamePad1.updateState();
            newGamePad2.updateState();

            currentPose = follower.getPose();


//            LL.detectPattern();

            // controls movement

            if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0 || gamepad1.right_stick_x != 0) {
                if (newGamePad1.left_trigger.state) {
                    Train.multi(-gamepad1.left_stick_y / 2, gamepad1.left_stick_x / 2,
                            gamepad1.right_stick_x / 2);
                } else {
                    Train.multi(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
                }
            } else {
                Train.stop();
            }

//            if (gamepad1.left_stick_y < -.4) {
//                // run the forward function from Functions program
//                Train.forward(speed);
//            } else if (gamepad1.left_stick_y > .4) {
//                // backwards
//                Train.backwards(speed);
//            } else if (gamepad1.left_stick_x < -.4) {
//                // Strafe left
//                Train.StraffLeft(speed);
//            } else if (gamepad1.left_stick_x > .4) {
//                // Strafe right
//                Train.StraffRight(speed);
//            } else if (gamepad1.right_stick_x < -.4) {
//                // left
//                Train.left(speed);
//            } else if (gamepad1.right_stick_x > .4) {
//                // right
//                Train.right(speed);
//            } else {
//                // run the stop function from training
//                Train.stop();
//            }


            //initialize speed as a variable
            telemetry.addData("Speed: ", speed);

//            if (Color.isGreen()) {
//                relativeLayout.setBackgroundColor(Color.greenV());
//            } else {
//                relativeLayout.setBackgroundColor(-1);
//            }

            // intake control
            if (gamepad2.left_stick_y > .4 || newGamePad2.left_trigger.state) {
                // grab ball
                Intake.intake(.9);
                Launcher.stop();
            } else if ((gamepad2.left_stick_y < -.4) && gamepad2.dpad_down) {
                // expel ball
                Intake.reverse(0.75);
            } else if (newGamePad2.a.state) {
                Train.stop();
                Launcher.autoLaunchClose();
            } else if (newGamePad2.x.released) {
                Train.stop();
                Launcher.autoLaunchFar();
            } else if (newGamePad2.y.state) {
//                Intake.Launch(0.5, 0.6);
                Intake.Sort();
            }
            // launcher control
            else if (newGamePad2.right_trigger.state) {
                Launcher.manualLauncher();
//                Launcher.getV();
            } else {
                Launcher.stop();
                Intake.FeedStop();
                Intake.stop();
            }

            // speed control
            // decrease drive speed variable
            if (newGamePad1.left_bumper.released) {
                // when left bumper is pressed, slows down movement universally
                speed -= (0.1);
                if (speed <= 0.1) {
                    speed = 0.1;
                }
                //increase speed variable
            } else if (newGamePad1.right_bumper.released) {
                // when right bumper is pressed, speeds up movement universally
                speed += (0.1);
                if (speed >= 1) {
                    speed = (1);
                }
            }

//            LLResult result = limelight.getLatestResult();
//            LL.getResult();
        }

    }
}