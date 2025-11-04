package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorColor;

public class Intake {

    SensorTraining Color = new SensorTraining();
    private DcMotor IntakeM = null;
    private LinearOpMode opmode = null;

    public Intake() {
    }

    public void init(LinearOpMode opMode) {

        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        IntakeM = hwMap.dcMotor.get("IntakeM");


        // forward, not necessary to set but still here
        IntakeM.setDirection(DcMotorSimple.Direction.REVERSE);


        // sets power to 0 to make sure nothing moves at first
        IntakeM.setPower(0);


    }
    public void intake(){
        // when intake is called, moves the motor forward with power value 1
        IntakeM.setPower(1);
    }
    public void outTake() {
        IntakeM.setPower(-1);
    }
    public void intakeStop(){
        IntakeM.setPower(0);
    }

    public void kick() {
        Color.color_telemetry();
    }
}
