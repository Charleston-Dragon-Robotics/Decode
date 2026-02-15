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

    public void autoLaunch() {
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
        opmode.telemetry.addLine("RLM Velocity: " + RLM.getVelocity());
        opmode.telemetry.addLine("LLM Velocity: " + LLM.getVelocity());
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
        Intake.FeedR();
        launch(1050);
        opmode.sleep(100);
        launch(1050);
        opmode.sleep(100);
        launch(1050);
    }
    public void autoLaunchClose() {
        Intake.FeedR();
        launch(950);
        opmode.sleep(100);
        launch(950);
        opmode.sleep(100);
        launch(950);
    }

    private void launch(double velocity)
    {
        manualLauncher();
        Intake.intake(0.85);
        opmode.sleep(450);
        while (opmode.opModeIsActive() && !isAtVelocity(velocity)){
            opmode.telemetry.addData("SpinUp isAtVelocity is", isAtVelocity(velocity));
            opmode.telemetry.addLine("RLM Velocity: " + RLM.getVelocity());
            opmode.telemetry.addLine("LLM Velocity: " + LLM.getVelocity());
            opmode.telemetry.update();
        }
        Intake.Bunch(0,.4);
        while(opmode.opModeIsActive() && RLM.getVelocity() > (velocity - 30) && LLM.getVelocity() > (velocity - 30))
        {
            Intake.Feed();
        }
        Intake.FeedStop();
    }
//    public void autoLaunchFar() {
//        manualLauncher();
//        opmode.telemetry.addLine("manualLaunch");
//        Intake.FeedR();
//        Intake.intake(0.2);
//        while (opmode.opModeIsActive() && !isAtVelocity(1050)){
//            opmode.telemetry.addData("SpinUp isAtVelocity is", isAtVelocity(1050));
//            opmode.telemetry.addLine("RLM Velocity: " + RLM.getVelocity());
//            opmode.telemetry.addLine("LLM Velocity: " + LLM.getVelocity());
//            opmode.telemetry.update();
//        }
//        opmode.telemetry.addData("first while done", "102");
//        Intake.Feed();
//        Intake.intake(.5);
//        opmode.sleep(1000);
//        Intake.stop();
//        opmode.telemetry.addLine("After 1st launch");
//        opmode.telemetry.update();
//        while (!isAtVelocity(1050)){
//            opmode.telemetry.addData("107 isAtVelocity is", isAtVelocity(1050));
//            opmode.telemetry.addLine("RLM Velocity: " + RLM.getVelocity());
//            opmode.telemetry.addLine("LLM Velocity: " + LLM.getVelocity());
//            opmode.telemetry.update();
//        }
//        opmode.telemetry.addData("2nd while done", "114");
//        Intake.intake(.5);
//        opmode.sleep(1000);
//        Intake.stop();
//        opmode.telemetry.addLine("After 2nd launch");
//        opmode.telemetry.update();
//        while (!isAtVelocity(1050)){
//            opmode.telemetry.addData("115 isAtVelocity is", isAtVelocity(1050));
//            opmode.telemetry.addLine("RLM Velocity: " + RLM.getVelocity());
//            opmode.telemetry.addLine("LLM Velocity: " + LLM.getVelocity());
//            opmode.telemetry.update();
//        };
//        Intake.intake(.5);
//        opmode.sleep(1000);
//        Intake.stop();
//        Intake.FeedStop();
//        opmode.telemetry.addLine("After 3rd launch");
//        opmode.telemetry.update();
//    }

//    public void autoLaunchClose() {
//        autoLaunch();
//        while (!isAtVelocity(950)){}
//        Intake.FeedR();
//        Intake.intake(0.2);
//        while (!isAtVelocity(950)){}
//        Intake.Feed();
//        Intake.intake(.5);
//        Intake.stop();
//        while (!isAtVelocity(950)){}
//        Intake.intake(.5);
//        Intake.stop();
//        while (!isAtVelocity(950)){}
//        Intake.stop();
//        Intake.FeedStop();
//    }
    public boolean isAtVelocity(double targetVelocity){
        double tolerence = 10.0;
        boolean rightRdy = false;
        boolean leftRdy = false;
        leftRdy = Math.abs(LLM.getVelocity()-targetVelocity) <= tolerence;
        rightRdy = Math.abs(RLM.getVelocity()-targetVelocity) <= tolerence;
        if (rightRdy == true&&leftRdy==true){
            return true;
        }else {
            return false;
        }

    }

}