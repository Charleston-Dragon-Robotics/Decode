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
import com.qualcomm.robotcore.hardware.Servo;
//import com.bylazar.

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
        Training Training = new Training();
        ServoTraining Servo = new ServoTraining();
        // initialize subassembly classes
        Training.init(this);
        Servo.init(this);
        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

//        LL.getStatus();

        waitForStart();

        while (opModeIsActive()) {

            newGamePad1.updateState();
            newGamePad2.updateState();

            // controls movement
            Training.motor();

            //initialize speed as a variable
//            telemetry.addData("Speed: ", speed);
//            telemetry.update();

            // intake control


            // launcher control

            // speed control
            // decrease drive speed variable

            //increase speed variable


            //something something servo i think i dont know this probably isnt good or correct at all
            Servo.normal();
            Servo.

        }
    }
}