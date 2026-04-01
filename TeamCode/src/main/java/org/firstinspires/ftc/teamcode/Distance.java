package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class Distance{

    private DistanceSensor SD;

    private LinearOpMode opMode = null;

    public Distance() {}

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        hwMap = opMode.hardwareMap;

        SD = hardwareMap.get(Rev2mDistanceSensor.class, "sensor_distance");

    }

    public double measureD(){
        return(SD.getDistance(DistanceUnit.INCH));
    }



}
