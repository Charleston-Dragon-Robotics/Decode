//package org.firstinspires.ftc.teamcode;
//
//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;
//
//import com.qualcomm.hardware.limelightvision.LLResult;
//import com.qualcomm.hardware.limelightvision.LLResultTypes;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.ColorSensor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.hardware.limelightvision.Limelight3A;
//
//import java.util.List;
//
//
//public class LLOpMode {
//    private Limelight3A limelight;
//
//    public void init(LinearOpMode opMode) {
//        HardwareMap hwMap;
//        hwMap = opMode.hardwareMap;
//
//        limelight = hwMap.get(Limelight3A.class, "limelight");
//        limelight.pipelineSwitch(7);
//    }
//
//
//    public void getResult() {
//        limelight.start();
//        List fiducialResult;
//        LLResult result = limelight.getLatestResult();
//
//        if (result.isValid()) {
//            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
//            for (LLResultTypes.FiducialResult fr : fiducialResults) {
//                opmode.telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f",
//                        fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
//                opmode.telemetry.update();
//            }
//        } else {
//            opmode.telemetry.addLine();
//            opmode.telemetry.update();
//        }
//    }
//}
