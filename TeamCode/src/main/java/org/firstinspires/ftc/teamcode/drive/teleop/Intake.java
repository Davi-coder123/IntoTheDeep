package org.firstinspires.ftc.teamcode.drive.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class Intake extends OpMode {
    DcMotor braco;
    Servo linkageRight;
    Servo linkageLeft;
    Servo pulsoRight;
    Servo pulsoLeft;
    Servo garra;
    Servo rotate;
    double ticks = 336;
    double newTarget;

    public void init(){
        braco = hardwareMap.get(DcMotor.class, "braco");
        linkageLeft = hardwareMap.get(Servo.class, "lleft");
        linkageRight = hardwareMap.get(Servo.class, "lright");
        pulsoLeft = hardwareMap.get(Servo.class, "pleft");
        pulsoRight = hardwareMap.get(Servo.class, "pright");
        garra = hardwareMap.get(Servo.class, "garra");
        rotate = hardwareMap.get(Servo.class, "rotate");

        braco.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        braco.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public void loop(){
        if (gamepad1.a){
            linkageRight.setPosition(1);
            linkageLeft.setPosition(0); // vai pra frente
        }
        if (gamepad1.b){
            linkageLeft.setPosition(1);
            linkageRight.setPosition(0); //volta
        }
        if (gamepad1.dpad_down){
            bracoColeta(2);
        }
        if (gamepad1.dpad_up){
            bracoVolta();
        }
        if (gamepad1.x){
            garra.setPosition(1); // garra pega
        }
        if(gamepad1.y){
            garra.setPosition(0); // garra solta
        }
        if(gamepad1.right_bumper){
            rotate.setPosition(1); // direita
        }
        if (gamepad1.left_bumper){
            rotate.setPosition(0); // esquerda
        }
    }
    public void bracoVolta() {
        braco.setTargetPosition(0);
        braco.setPower(1);
        braco.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pulsoRight.setPosition(0); // braco volta
        pulsoLeft.setPosition(1);
    }
    public void bracoColeta(int turnage) {
        newTarget = ticks / turnage;
        braco.setTargetPosition((int) newTarget);
        braco.setPower(1);
        braco.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pulsoRight.setPosition(1); // braco coleta
        pulsoLeft.setPosition(0);
    }

}
