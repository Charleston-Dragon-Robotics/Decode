//package org.firstinspires.ftc.teamcode;
////
//import com.qualcomm.hardware.limelightvision.LLResult;
//import com.qualcomm.hardware.limelightvision.LLResultTypes;
//import com.qualcomm.hardware.limelightvision.LLStatus;
//import com.qualcomm.hardware.limelightvision.Limelight3A;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
////
//import org.firstinspires.ftc.teamcode.Subassys.Drivetrain;
//
//import java.util.List;
//
//public class Limelight {
//    private LinearOpMode opmode = null;
////
//    public Limelight3A limelight;
//
//    Drivetrain Drive = new Drivetrain();
//
//    public Limelight() {
//    }
//
//    public void init(LinearOpMode opMode) {
//
//        opmode = opMode;
//        HardwareMap hwMap;
//        hwMap = opmode.hardwareMap;
//
//
//        opmode.telemetry.addLine("Limelight initialize...");
//        opmode.telemetry.update();
//
//
//        limelight = hwMap.get(Limelight3A.class, "limelight");
//        opmode.telemetry.setMsTransmissionInterval(11);
//        limelight.pipelineSwitch(7);
//    }
//
//    public void getStatus() {
//        limelight.start();
//        LLStatus status = limelight.getStatus();
//        opmode.telemetry.addData("Name", "%s", status.getName());
//        opmode.telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
//                status.getTemp(), status.getCpu(), (int) status.getFps());
//        opmode.telemetry.addData("Pipeline", "Index: %d, Type: %s",
//                status.getPipelineIndex(), status.getPipelineType());
//        opmode.telemetry.update();
//    }
//
//    public void detectPattern() {
//        limelight.start();
//        LLResult result = limelight.getLatestResult();
//        if (result.isValid()) {
//            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
//            for (LLResultTypes.FiducialResult fr : fiducialResults) {
//                //switch to display pattern based on April Tag
//                switch (fr.getFiducialId()) {
//                    case 21:
//                        opmode.telemetry.addLine("GPP");
//                        break;
//                    case 22:
//                        opmode.telemetry.addLine("PGP");
//                        break;
//                    case 23:
//                        opmode.telemetry.addLine("PPG");
//                        break;
//                    default:
//
//                        break;
//                }
////                opmode.telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
//            }
//        } else {
//            opmode.telemetry.addData("Limelight", "No data available");
//        }
//        opmode.telemetry.update();
//    }
//}
