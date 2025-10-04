package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Outtake {
    private DcMotor OuttakeRM = null;
    private DcMotor OuttakeLM = null;

    private LinearOpMode opmode = null;

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        OuttakeRM = hwMap.dcMotor.get("OuttakeRM");
        OuttakeLM = hwMap.dcMotor.get("OuttakeLM");

        // LEFT motor is backwards for now
        OuttakeRM.setDirection(DcMotorSimple.Direction.FORWARD);
        OuttakeLM.setDirection(DcMotorSimple.Direction.REVERSE);

        // power set to 0 as usual
        OuttakeRM.setPower(0);
        OuttakeLM.setPower(0);
    }
    public void outtake(){
        // starts the outtake motors when called
        OuttakeRM.setPower(1);
        OuttakeLM.setPower(1);
    }
    public void outtakestop(){
        // unsurprisingly stops the outtake
        OuttakeRM.setPower(0);
        OuttakeLM.setPower(0);
    }
}
