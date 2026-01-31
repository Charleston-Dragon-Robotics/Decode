package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.bylazar.

public class Launcher {

    private DcMotorEx LLM;
    private DcMotorEx RLM;
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

        double velocity = LLM.getVelocity();

        Intake.init(opMode);
    }

    public void autoLaunch(double power) {
        RLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RLM.setVelocity(700);
        LLM.setVelocity(700);

//        Intake.Launch(1,1);
    }

    public void manualLauncher(double power) {
        RLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RLM.setVelocity(1200);
        LLM.setVelocity(1200);
        // maybe 1050?
    }

    public void getV() {
        opmode.telemetry.addLine("Velocity: " + RLM.getVelocity());
        opmode.telemetry.update();
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