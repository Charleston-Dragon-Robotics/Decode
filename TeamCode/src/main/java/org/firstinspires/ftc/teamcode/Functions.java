package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.pedropathing.Drivetrain;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


import com.pedropathing.util.PoseHistory;
import com.pedropathing.follower.Follower;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Functions {
    //intalise varible
    public static Follower follower;
    static PoseHistory poseHistory;


    private LinearOpMode opmode = null;

    Training Drive = new Training();
//    Limelight LL = new Limelight();
    Intake intake = new Intake();


    public Functions() {
    }



    public void init(LinearOpMode opMode) {

    }
//creates function
    public void forwardForDist(double target, double speed) {
        while (Math.abs(follower.getPose().getX()) < target) {
            Drive.forward(speed);
        }
        Drive.stop();
    }


    public void sort (){
        intake.intake(0.6);
        opmode.sleep(500);
        intake.stop();
    }

//    public void turn2LL() {
////        LLResult result = LL.limelight.getLatestResult();
////        while(!result.isValid()) {
////            Drive.right(0.5);
////        }
////        Drive.stop();
//    }

}

//    if(Math.abs(follower.getPose().
//
//    getX())>
//    double distance)
//
//    {
//        end = true;
//    }

