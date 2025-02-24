package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class Servos extends OpMode {
    Servo garra;
    Servo pulso;
    Servo bucket;
    Servo libera;
    public void init() {
        garra = hardwareMap.get(Servo.class, "garra");
        pulso = hardwareMap.get(Servo.class, "pulso");
        bucket = hardwareMap.get(Servo.class, "bucket");
        libera = hardwareMap.get(Servo.class, "libera");
    }
    public void loop(){
        if (gamepad1.a){
            garra.setPosition(0.2);
        }
        if (gamepad1.b){
            garra.setPosition(0.45);
        }
        if (gamepad1.x){
            pulso.setPosition(0.2);
        }
        if (gamepad1.y){
            pulso.setPosition(0.8);
        }
        if (gamepad1.dpad_left){
            bucket.setPosition(1);
        }
        if (gamepad1.dpad_right){
            bucket.setPosition(0.1);
        }
        if (gamepad1.right_bumper){
            libera.setPosition(1);
        }
        if (gamepad1.left_bumper){
            libera.setPosition(0.5);
        }
    }
}