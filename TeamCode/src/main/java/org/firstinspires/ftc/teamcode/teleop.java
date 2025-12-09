package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.bylazar.
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.GamepadStates;

import java.util.List;

// this too


@TeleOp(name = "Teleop", group = "Teleop")
// this is the thing that we run
public class teleop extends LinearOpMode {

    View relativeLayout;

    private Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException {

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        // declare subassembly classes
        Training Train = new Training();
        Launcher Launcher = new Launcher();
        Intake Intake = new Intake();
        ServoTraining Servo = new ServoTraining();
//        Limelight LL = new Limelight();
        Color Color = new Color();

        // initialize subassembly classes
        Train.init(this);
        Intake.init(this);
        Launcher.init(this);
        Servo.init(this);
//        LL.init(this);
        Color.init(this);
        //List fiducialResult;

        //    Create motor objects here
        DcMotor FrontRM = null;
        DcMotor FrontLM = null;
        DcMotor BackRM = null;
        DcMotor BackLM = null;
        LinearOpMode opmode = null;

        HardwareMap hwmap;
        opmode = (LinearOpMode) opMode;
        hwmap = opMode.hardwareMap;


        //limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        telemetry.setMsTransmissionInterval(11);
//        limelight.pipelineSwitch(7);
//        limelight.start();

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

//        LL.getStatus();

        double speed = 0.5;

        waitForStart();

        while (opModeIsActive()) {

            newGamePad1.updateState();
            newGamePad2.updateState();



            // name motor objects here
            FrontRM = hwmap.dcMotor.get("FrontRM");
            FrontLM = hwmap.dcMotor.get("FrontLM");
            BackRM = hwmap.dcMotor.get("BackRM");
            BackLM = hwmap.dcMotor.get("BackLM");

            double axial = gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;
            double max;

            // directions!!!
            FrontLM.setDirection(DcMotorSimple.Direction.REVERSE);
            FrontRM.setDirection(DcMotorSimple.Direction.FORWARD);
            BackLM.setDirection(DcMotorSimple.Direction.REVERSE);
            BackRM.setDirection(DcMotorSimple.Direction.FORWARD);


            double FrontLMPower = (axial + lateral + yaw);
            double FrontRMPower = (axial - lateral - yaw);
            double BackLMPower = (axial - lateral + yaw);
            double BackRMPower = (axial + lateral - yaw);

            FrontLM.setPower(FrontLMPower);
            FrontRM.setPower(FrontRMPower);
            BackLM.setPower(BackLMPower);
            BackRM.setPower(BackRMPower);

            max = Math.max(Math.abs(FrontLMPower), Math.abs(FrontRMPower));
            max = Math.max(max, Math.abs(BackLMPower));
            max = Math.max(max, Math.abs(BackRMPower));

            if (max > 1.0) {
                FrontLMPower /= max;
                FrontRMPower /= max;
                BackLMPower /= max;
                BackRMPower /= max;
            }

//            LL.detectPattern();

//            // controls movement
//            if (gamepad1.left_stick_y < -.4) {
//                // run the forward function from Functions program
//                Train.forward(speed);
//            } else if (gamepad1.left_stick_y > .4) {
//                // backwards
//                Train.backwards(speed);
//            } else if (gamepad1.left_stick_x <-.4) {
//                Train.StraffLeft(speed);
//            } else if (gamepad1.left_stick_x > .4) {
//                Train.StraffRight(speed);
//            }else if (gamepad1.right_stick_x < -.4) {
//                // left
//                Train.left(speed);
//            } else if (gamepad1.right_stick_x > .4) {
//                // right
//                Train.right(speed);
//            } else {
//                // run the stop function from training
//                Train.stop();
//            }
//
//
//
//            //initialize speed as a variable
//            telemetry.addData("Speed: ", speed);
//            telemetry.update();

//            if (Color.isGreen()) {
//                relativeLayout.setBackgroundColor(Color.greenV());
//            } else {
//                relativeLayout.setBackgroundColor(-1);
//            }

            // intake control
            if (gamepad2.left_stick_y > .4) {
                // grab ball
                Intake.intake(1);
            } else if (gamepad2.left_stick_y < -.4) {
                // expel ball
                Intake.reverse();
            } else if (gamepad2.left_stick_x > .4) {
                Intake.stop();
            } else if (gamepad2.left_stick_x < -.4) {
                Intake.stop();
            } else {
                Intake.intake(.4);
            }
            if (gamepad2.dpad_down) {
                // grab ball
                Intake.intake(1);
            } else if (gamepad2.dpad_up) {
                // expel ball
                Intake.reverse();
            } else if (gamepad2.dpad_left) {
                Intake.stop();
            } else if (gamepad2.dpad_right) {
                Intake.intake(.4);
            } else {
                Intake.stop();
            }

            // launcher control
            if (gamepad2.right_stick_y < -.4) {
                Launcher.manualLauncher();
            } else if (newGamePad2.a.state) {
                Launcher.manualLauncher();
            } else {
                Launcher.stop();
            }


//            // speed control
//            // decrease drive speed variable
//            if (newGamePad1.left_bumper.released) {
//                // when left bumper is pressed, slows down movement universally
//                speed -= (0.1);
//                if (speed <= 0.1) {
//                    speed = 0.1;
//                }
//            //increase speed variable
//            } else if (newGamePad1.right_bumper.released) {
//                // when right bumper is pressed, speeds up movement universally
//                speed += (0.1);
//                if (speed >= 1) {
//                    speed = (1);
//                }
//            }
//            LLResult result = limelight.getLatestResult();
//            LL.getResult();
        }
    }
}