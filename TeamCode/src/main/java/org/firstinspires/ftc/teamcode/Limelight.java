package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Limelight {
    private LinearOpMode opmode = null;

    private Limelight3A limelight;

    private double distance;

    IMU imu;

    public Limelight() {
    }

    public void init(LinearOpMode opMode) {

        opmode = opMode;
        HardwareMap hwMap;
        hwMap = opmode.hardwareMap;

        opmode.telemetry.addLine("Limelight initialize...");
        opmode.telemetry.update();
        // adds text to the driverhub

        imu = hwMap.get(IMU.class, "imu"); // Replace "imu" with your configured name


        limelight = hwMap.get(Limelight3A.class, "limelight");
        opmode.telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(7);

        // sets the pipeline to 7, for detecting patterns :3
    }

    public void getStatus() {
        limelight.start();
        LLStatus status = limelight.getStatus();
        opmode.telemetry.addData("Name", "%s", status.getName());
        opmode.telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                status.getTemp(), status.getCpu(), (int) status.getFps());
        opmode.telemetry.addData("Pipeline", "Index: %d, Type: %s",
                status.getPipelineIndex(), status.getPipelineType());
        opmode.telemetry.update();
        // this starts the limelight, adds the data for it on the driver hub, and updates the telemetry.
    }
    public void locationTest() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw(AngleUnit.DEGREES));

        LLResult result = limelight.getLatestResult();
        if  (result != null && result.isValid()) {
            Pose3D botpose = result.getBotpose_MT2();
            opmode.telemetry.addData("Calculated Distance", distance);
            opmode.telemetry.addData("Target X", result.getTx());
        }
    }

    public void detectPattern() {
        limelight.start();

        LLResult result = limelight.getLatestResult();

        if(result.isValid()) {
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fr : fiducialResults) {
                switch (fr.getFiducialId()) {
                    case 21:
                        opmode.telemetry.addLine("GPP");
                        break;
                    case 22:
                        opmode.telemetry.addLine("PGP");
                        break;
                    case 23:
                        opmode.telemetry.addLine("PPG");
                        break;
                    default:
                        break;
                        // starts the limelight, reads for any april tag patterns. if it gets 21 22 or 23 it outputs GPP PGP or PGP
                }
//                opmode.telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
            }
        } else {
            opmode.telemetry.addData("Limelight", "No data available");
            // if no pattern is detected, outputs nothing.
        }
        opmode.telemetry.update();
        // updates the telemetry.
    }
}
