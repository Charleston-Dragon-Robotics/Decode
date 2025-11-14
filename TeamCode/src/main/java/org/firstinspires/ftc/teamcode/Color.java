package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

public class Color {
    private LinearOpMode opmode = null;

    com.qualcomm.robotcore.hardware.ColorSensor Color;
    float[] hsv={0F,0F,0F};

    String isPurple;

    public Color() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;
        opMode.telemetry.addLine("Initializing color sensor...");
        opMode.telemetry.update();

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        Color = hwMap.get(ColorSensor.class,"Color");
    }

    public void HSV() {


        opmode.telemetry.addData("H: ", JavaUtil.colorToHue(Color.argb()));
        opmode.telemetry.addData("S: ", JavaUtil.colorToSaturation(Color.argb()));
        opmode.telemetry.addData("V: ", JavaUtil.colorToValue(Color.argb()));
        opmode.telemetry.update();

    }

    public String ispurpleF() {
        float Hue = JavaUtil.colorToHue(Color.argb());
        if (190 > Hue && Hue > 90) {
            isPurple = "green";
        } else if (Hue > 190) {
            isPurple = "purple";
        } else {
            isPurple = "none";
        }
        return isPurple;
    }
    public void printColor (){
        opmode.telemetry.addLine("Is it purple?" +isPurple);
        opmode.telemetry.update();
    }
}
