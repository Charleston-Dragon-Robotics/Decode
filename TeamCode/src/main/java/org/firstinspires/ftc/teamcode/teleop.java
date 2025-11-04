package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
//import com.bylazar.

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.GamepadStates;

import java.util.List;

import kotlin._Assertions;
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


        Training Train = new Training();
        Intake Intake = new Intake();
        ServoTraining Servo = new ServoTraining();
//        Limelight LL = new Limelight();
        Color Color = new Color();
        Train.init(this);
        Intake.init(this);
        Servo.init(this);
//        LL.init(this);
        Color.init(this);
        //List fiducialResult;


        //limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        telemetry.setMsTransmissionInterval(11);
//        limelight.pipelineSwitch(7);
//        limelight.start();

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        telemetry.addData(">", "Robot Ready.  Press Play.");
        telemetry.update();

//        LL.getStatus();

        double speed = 0.5;

        waitForStart();

        while (opModeIsActive()) {

            newGamePad1.updateState();
            newGamePad2.updateState();


//            LL.detectPattern();

            // (pretty much while this is running)
            if (gamepad1.left_stick_y < -.4) {
                // run the forward function from Functions program
                Train.forward(speed);
            } else if (gamepad1.left_stick_y > .4) {
                // backwards
                Train.backwards(speed);
            } else if (gamepad1.left_stick_x <-.4) {
                Train.StraffLeft(speed);
            } else if (gamepad1.left_stick_x > .4) {
                Train.StraffRight(speed);
            }else if (gamepad1.right_stick_x < -.4) {
                // left
                Train.left(speed);
            } else if (gamepad1.right_stick_x > .4) {
                // right
                Train.right(speed);
            } else {
                // run the stop function from training
                Train.stop();
            }




            telemetry.addData("Speed: ", speed);
            telemetry.update();

//            if (Color.isGreen()) {
//                relativeLayout.setBackgroundColor(Color.greenV());
//            } else {
//                relativeLayout.setBackgroundColor(-1);
//            }

            if (gamepad2.right_stick_y < -.4) {
                Intake.intake();
            }else if(gamepad2.right_stick_y > .4) {
                Intake.outTake();
            }
            else {
                Intake.intakeStop();
            }

//            if (gamepad2.right_stick_y > .4) {
//            Intake.backwards(speed);
//            }

//            if (newGamePad1.a.released) {
//                Servo.normal();
//                // normal means 0
//            } else if (newGamePad1.b.released) {
//                Servo.right();
//                // right means 1
//            } else if (newGamePad1.x.released) {
//                Servo.left();
//                // left means -1
//            }

            if (newGamePad1.left_bumper.released) {
                // when left bumper is pressed, slows down movement universally
                speed -= (0.1);
                if (speed <= 0.1) {
                    speed = 0.1;
                }

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