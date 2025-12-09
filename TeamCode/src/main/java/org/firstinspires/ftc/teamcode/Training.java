package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.GamepadStates;


public class Training {

//    Create motor objects here
    private DcMotor FrontRM = null;
    private DcMotor FrontLM = null;
    private DcMotor BackRM = null;
    private DcMotor BackLM = null;

private LinearOpMode opmode = null;


    public Training() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        // name motor objects here
        FrontRM = hwMap.dcMotor.get("FrontRM");
        FrontLM = hwMap.dcMotor.get("FrontLM");
        BackRM = hwMap.dcMotor.get("BackRM");
        BackLM = hwMap.dcMotor.get("BackLM");

        double axial = gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double yaw = gamepad1.right_stick_x;
        double max;

        // directions!!!
        FrontLM.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontRM.setDirection(DcMotorSimple.Direction.FORWARD);
        BackLM.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRM.setDirection(DcMotorSimple.Direction.FORWARD);


        double FrontLMPower = (axial + lateral + yaw);
        double FrontRMPower = (axial - lateral - yaw);
        double BackLMPower = (axial - lateral + yaw);
        double BackRMPower = (axial + lateral - yaw);

        FrontLM.setPower(FrontLMPower);
        FrontRM.setPower(FrontRMPower);
        BackLM.setPower(BackLMPower);
        BackRM.setPower(BackRMPower);

        max = Math.max(Math.abs(FrontLMPower), Math.abs(FrontRMPower));
        max = Math.max(max, Math.abs(BackLMPower));
        max = Math.max(max, Math.abs(BackRMPower));

        if (max > 1.0) {
            FrontLMPower /= max;
            FrontRMPower /= max;
            BackLMPower /= max;
            BackRMPower /= max;
        }

    }

    public void forward(double speed) {
        // set powers for motors to go forward here
        FrontRM.setPower(speed);
        FrontLM.setPower(speed);
        BackRM.setPower(speed);
        BackLM.setPower(speed);
    }
    public void backwards(double speed) {
        // set powers for motors to go backwards here
        FrontRM.setPower(-speed);
        FrontLM.setPower(-speed);
        BackRM.setPower(-speed);
        BackLM.setPower(-speed);
    }
    public void right(double speed) {
        // set powers for motors to go right here
        FrontRM.setPower(-speed);
        FrontLM.setPower(speed);
        BackRM.setPower(-speed);
        BackLM.setPower(speed);
    }
    public void left(double speed) {
        // set powers for motors to go left here
        FrontRM.setPower(speed);
        FrontLM.setPower(-speed);
        BackRM.setPower(speed);
        BackLM.setPower(-speed);
}
    public void StraffRight(double speed) {
        // set powers for motors to straff right here
        FrontRM.setPower(-speed);
        FrontLM.setPower(speed);
        BackRM.setPower(speed);
        BackLM.setPower(-speed);
    }
    public void StraffLeft(double speed) {
        // set powers for motors to straffs left here
        FrontRM.setPower(speed);
        FrontLM.setPower(-speed);
        BackRM.setPower(-speed);
        BackLM.setPower(speed);
    }
    public void stop() {
        // set motor power to stop
        FrontRM.setPower(0);
        FrontLM.setPower(0);
        BackRM.setPower(0);
        BackLM.setPower(0);
    }

}
