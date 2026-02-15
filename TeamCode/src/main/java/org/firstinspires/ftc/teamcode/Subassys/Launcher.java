package org.firstinspires.ftc.teamcode.Subassys;

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

    org.firstinspires.ftc.teamcode.Subassys.Intake Intake = new Intake();

    public void init(LinearOpMode opMode) {
        HardwareMap hwmap;

        opmode = opMode;
        hwmap = opMode.hardwareMap;

        LLM = (DcMotorEx) hwmap.dcMotor.get("LLM");
        RLM = (DcMotorEx) hwmap.dcMotor.get("RLM");


        LLM.setDirection(DcMotorSimple.Direction.FORWARD);
        RLM.setDirection(DcMotorSimple.Direction.REVERSE);

        LLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LLM.setPower(0);
        RLM.setPower(0);

        double velocity = LLM.getVelocity();

        Intake.init(opMode);
    }

    public void autoLaunch(double power) {
        RLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RLM.setVelocity(950);
        LLM.setVelocity(950);

//        Intake.Launch(1,1);
    }

    public void manualLauncher() {
        RLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RLM.setVelocity(1050);
        LLM.setVelocity(1050);
        // maybe 1050?
        // it is 1050 lol
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

    public void autoLaunchFar() {
        manualLauncher();
        opmode.sleep(800);
        Intake.FeedR();
        Intake.intake(0.2);
        opmode.sleep(800);
        Intake.Feed();
        Intake.intake(.5);
        Intake.stop();
        opmode.sleep(800);
        Intake.intake(.5);
        opmode.sleep(2000);
        stop();
        Intake.stop();
        Intake.FeedStop();
    }

    public void autoLaunchClose() {
        autoLaunch(.5);
        Intake.FeedR();
        Intake.intake(0.2);
        opmode.sleep(800);
        Intake.Feed();
        Intake.intake(.5);
//        Intake.stop();
//        Intake.intake(.5);
        opmode.sleep(2000);
        stop();
        Intake.stop();
        Intake.FeedStop();
    }
    public boolean isAtVelocity(double targetVelocity){
        double tolerence = 25.0;
        boolean rightRdy = false;
        boolean leftRdy = false;
        leftRdy = Math.abs(LLM.getVelocity()-targetVelocity) < tolerence;
        rightRdy = Math.abs(RLM.getVelocity()-targetVelocity) < tolerence;
        if (rightRdy == true&&leftRdy==true){
            return true;
        }else {
            return false;
        }

    }

}