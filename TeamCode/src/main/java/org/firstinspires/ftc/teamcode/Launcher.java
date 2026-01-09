package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.bylazar.

public class Launcher {

    private DcMotorEx LLM = null;
    private DcMotorEx RLM = null;
    private LinearOpMode opmode = null;

    public Launcher() {
    }

    Intake Intake = new Intake();


    public void init(LinearOpMode opMode) {
        HardwareMap hwmap;

        opmode = opMode;
        hwmap = opMode.hardwareMap;

        LLM = (DcMotorEx) hwmap.dcMotor.get("LLM");
        RLM = (DcMotorEx) hwmap.dcMotor.get("RLM");


        LLM.setDirection(DcMotorSimple.Direction.FORWARD);
        RLM.setDirection(DcMotorSimple.Direction.REVERSE);

        LLM.setPower(0);
        RLM.setPower(0);
    }

    public void autoLaunch() {
        LLM.setPower(3000);
        RLM.setPower(3000);
        Intake.Launch(1,1);
    }

    public void manualLauncher(double speed) {
        RLM.setPower(speed);
        LLM.setPower(speed);
        Intake.FeederM.setPower(1);
        getV();
    }

    public double getV() {
        return RLM.getVelocity();
    }

    public void resetEncoders() {
        RLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runEncoders() {
//        LLM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stopEncoders() {
        LLM.setPower(0);
        RLM.setPower(0);
    }

    public void stop() {
        RLM.setPower(0);
        LLM.setPower(0);
    }
}