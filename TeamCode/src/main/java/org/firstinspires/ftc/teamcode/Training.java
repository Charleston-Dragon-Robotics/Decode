package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Training {

//    Create motor objects here
    private DcMotor RightMotor;

private LinearOpMode opmode = null;


    public Training() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        // name motor objects here
        RightMotor = hwMap.dcMotor.get("RightMotor");

        // directions!!!
        RightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // set motor powers to 0
        RightMotor.setPower(0);

    }
    public void motor (){
        RightMotor.setPower(1);
    }


}
