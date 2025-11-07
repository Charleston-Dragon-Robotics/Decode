package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoTraining {
    private LinearOpMode opmode = null;

    //intalise servos
    private Servo servo = null;


    public ServoTraining() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;
        servo = hwMap.servo.get("Servo");
    }

    //creates servo functions
    public void normal(){
        servo.setPosition(0);
    }
    public void right(){
        servo.setPosition(1);
    }
    public void left(){
        servo.setPosition(-1);
    }
}