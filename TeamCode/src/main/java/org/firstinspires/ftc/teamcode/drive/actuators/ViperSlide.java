package org.firstinspires.ftc.teamcode.drive;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ViperSlide extends OpMode{
    DcMotor motor;
    DcMotor poliaalta;
    double ticks = 2800.5;
    double newTarget;
    Servo garra;
    Servo pulso;
    Servo bucket;
    @Override
    public void init(){
        motor = hardwareMap.get(DcMotor.class, "poliabaixa");
        poliaalta = hardwareMap.get(DcMotor.class, "poliaalta");
        telemetry.addData("Hardware: ", "Initialized");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        poliaalta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        poliaalta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        garra = hardwareMap.get(Servo.class, "garra");
        pulso = hardwareMap.get(Servo.class, "pulso");
        bucket = hardwareMap.get(Servo.class, "bucket");

        bucket.setPosition(0.13);
        pulso.setPosition(0.4);
    }

    @Override
    public void loop(){
        double vai = gamepad1.left_stick_y;
        motor.setPower(vai);

        if (gamepad1.a){
            garra.setPosition(0.2);
        }
        if (gamepad1.b){
            garra.setPosition(0.5);
        }
        if (gamepad1.x){
            pulso.setPosition(0.25);
        }
        if (gamepad1.y){
            pulso.setPosition(0.81);
        }
        if (gamepad1.right_bumper){
            bucket.setPosition(0.8);
        }
        if (gamepad1.left_bumper){
            bucket.setPosition(0.13);
        }
        if (gamepad1.dpad_up) {
            poliaAltaUp(1);
            pulso.setPosition(0.4);
        }
        if (gamepad1.dpad_down){
            poliaAltaDown();
            sleep(4000);
            poliaalta.setPower(0);
        }
        if (gamepad1.dpad_left){
            poliaClimberDown();
        }
    }
    public void poliaAltaUp(int turnage) {
        newTarget = ticks / turnage;
        poliaalta.setTargetPosition((int) newTarget);
        poliaalta.setPower(0.8);
        poliaalta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void poliaAltaDown() {
        poliaalta.setTargetPosition(0);
        poliaalta.setPower(0.8);
        poliaalta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void poliaClimberDown() {
        poliaalta.setTargetPosition(0);
        poliaalta.setPower(1);
        poliaalta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}