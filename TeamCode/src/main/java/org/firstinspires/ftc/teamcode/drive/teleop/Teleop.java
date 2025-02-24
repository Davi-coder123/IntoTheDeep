package org.firstinspires.ftc.teamcode.drive.teleop;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Teleop extends OpMode {
    //COLETA
    Servo linkageRight;
    Servo linkageLeft;
    Servo pulsoRight;
    Servo pulsoLeft;
    Servo garra;
    Servo rotate;

    //ENTREGA
    DcMotor polia;
    Servo turn;
    Servo garrinha;
    double ticks = 2800.5;
    double ticks2 = 1000;
    double ticks3 = 2000;
    double newTarget;

    @Override
    public void init(){
        //COLETA
        linkageLeft = hardwareMap.get(Servo.class, "lleft");
        linkageRight = hardwareMap.get(Servo.class, "lright");
        pulsoLeft = hardwareMap.get(Servo.class, "pleft");
        pulsoRight = hardwareMap.get(Servo.class, "pright");
        garra = hardwareMap.get(Servo.class, "garra");
        rotate = hardwareMap.get(Servo.class, "rotate");

        //ENTREGA
        turn = hardwareMap.get(Servo.class, "turn");
        garrinha = hardwareMap.get(Servo.class, "garrinha");
        polia = hardwareMap.get(DcMotor.class, "polia");
        polia.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        polia.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // LEMBRA DE SETAR AS PRE-POSIÇÕES DOS SERVOS
    }
    @Override
    public void loop(){
        //COLETA
        if (gamepad1.dpad_up){
            linkageRight.setPosition(0.7);
            linkageLeft.setPosition(0.3); // vai pra frente
        }
        if (gamepad1.dpad_down){
            linkageLeft.setPosition(0.7);
            linkageRight.setPosition(0.3); //volta
        }
        if (gamepad1.x){
            pulsoRight.setPosition(0.9); // pulso desce
            pulsoLeft.setPosition(0);
        }
        if (gamepad1.y){
            pulsoRight.setPosition(0); // pulso sobe
            pulsoLeft.setPosition(0.85);
        }
        if (gamepad1.b){
            garra.setPosition(1); // garra pega
        }
        if(gamepad1.a){
            garra.setPosition(0.4); // garra solta
        }
        if(gamepad1.right_bumper){
            rotate.setPosition(1); // vira pro lado
        }
        if (gamepad1.left_bumper){
            rotate.setPosition(0.65); // meio
        }

        //ENTREGA
        if (gamepad2.dpad_up) {
            poliaBasket(1); //polia sobe na altura do high basket
        }
        if (gamepad2.dpad_left){
            poliaClip(2); // tira o clip da parede/ abaixa pra clipar
        }
        if (gamepad2.dpad_down){
            turn.setPosition(0.3); // polia desce
            poliaDown();
        }
        if (gamepad2.dpad_right){
            poliaClipar(1); // polia sobe na altura pra clipar
        }
        if (gamepad2.b){
            garrinha.setPosition(1); // fecha
        }
        if (gamepad2.a) {
            garrinha.setPosition(0.6); // abre
        }
        if (gamepad2.x){
            turn.setPosition(0.3); // volta o bracinho
        }
        if (gamepad2.y){
            turn.setPosition(1); // vira o bracinho
        }
    }
    public void poliaBasket(int turnage) {
        newTarget = ticks / turnage;
        polia.setTargetPosition((int) newTarget);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(200);
        turn.setPosition(0.85);
    }
    public void poliaClip(int turnage) {
        newTarget = ticks2 / turnage;
        polia.setTargetPosition((int) newTarget);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void poliaClipar(int turnage){
        newTarget = ticks3 / turnage;
        polia.setTargetPosition((int) newTarget);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void poliaDown() {
        polia.setTargetPosition(0);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(200);
        garrinha.setPosition(0.6);
        sleep(2000);
        polia.setPower(0);
    }
}

/*
        CONTROLES

START A: JOSÉ - COLETA
- DPAD-UP = linkage pra frente
- DPAD-DOWN = linkage pra trás
- X = pulso desce
- Y = pulso sobe
- A = garra abre
- B = garra fecha
- RIGHT_BUMPER = garra vira pra direita
- LEFT_BUMPER = garra volta pro meio

START B: DAVI - ENTREGA/CHASSI
- DPAD-UP = polia sobe na altura do high baket
- DPAD-LEFT = polia sobe pra tirar o clip da parede
- DPAD-RIGHT = polia sobe na altura pra clipar
- DPAD-DOWN = polia desce
- X = bracinho volta
- Y = bracinho vira
- A = garrinha abre
- B = garrinha fecha
- JOYSTICK_LEFT = Move/Strafe
- JOYSTICK_RIGHT = Turn


CONTROL HUB
Motores:
0 - Polia
1 - RightOdo
2 - LeftOdo
3 - MidOdo

Servos:
0 - rotate
1 - garra
2 - pulso esquerda
3 - pulso direita
4 - linkage esquerda
5 - linkage direita

EXPANSION HUB
Motores:
0 -
1 -
2 -
3 -

Servos:
0 - bracinho
1 - garrinha
2 -
3 -
4 -
5 -

 */
