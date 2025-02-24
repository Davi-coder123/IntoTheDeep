package org.firstinspires.ftc.teamcode.drive.autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoBasket4", group = "Linear Opmode")
public class AutonomoBasket4 extends LinearOpMode {

    // Declare motor variables
    private DcMotor frontLeft, frontRight, backLeft, backRight;

    // Conversion factor: encoder ticks per inch
    private static final double COUNTS_PER_MOTOR_REV = 336; // Rev HD Hex Motor
    private static final double WHEEL_DIAMETER_INCHES = 3.77;  // Diameter of the wheel in inches
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (Math.PI * WHEEL_DIAMETER_INCHES);

    Servo linkageRight;
    Servo linkageLeft;
    Servo pulsoRight;
    Servo pulsoLeft;
    Servo garra;
    Servo rotate;
    DcMotor polia;
    Servo bracinho;
    Servo garrinha;
    double ticks = 2800.5;
    double ticks2 = 1000;
    double ticks3 = 1400;
    double newTarget;

    @Override
    public void runOpMode() {
        // Initialize hardware
        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        backRight = hardwareMap.get(DcMotor.class, "BR");

        // Set motor directions
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // Set motors to use encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linkageLeft = hardwareMap.get(Servo.class, "lleft");
        linkageRight = hardwareMap.get(Servo.class, "lright");
        pulsoLeft = hardwareMap.get(Servo.class, "pleft");
        pulsoRight = hardwareMap.get(Servo.class, "pright");
        garra = hardwareMap.get(Servo.class, "garra");
        rotate = hardwareMap.get(Servo.class, "rotate");
        bracinho = hardwareMap.get(Servo.class, "turn");
        garrinha = hardwareMap.get(Servo.class, "garrinha");
        polia = hardwareMap.get(DcMotor.class, "polia");
        polia.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        polia.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        pulsoRight.setPosition(0.9);
        pulsoLeft.setPosition(0);
        linkageRight.setPosition(1);
        linkageLeft.setPosition(0);
        rotate.setPosition(0.65);
        garrinha.setPosition(1);
        bracinho.setPosition(0.4);

        waitForStart();

        // + esquerda - direita
        driveInches(6,0.4);
        Entrega();
        strafeInches(-10.7,0.3);
        sleep(200);
        turnDegrees(100,0.3);
        sleep(500);
        Coleta1();
        sleep(200);
        Coleta2();
        strafeInches(-4,0.3);
        sleep(300);
        turnDegrees(-30,0.3);
        Entrega();
        turnDegrees(40,0.3);
        sleep(200);
        Coleta1();
        sleep(200);
        Coleta2();
        turnDegrees(-40,0.3);
        Entrega();
        turnDegrees(30,0.3);
        strafeInches(-6.5,0.3);
        turnDegrees(15,0.3);
        Coleta1();
        sleep(200);
        Coleta2();
        turnDegrees(-30,0.3);
        Entrega();
    }
    private void driveInches(double inches, double speed) {
        int targetPosition = (int) (inches * COUNTS_PER_INCH);

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + targetPosition);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + targetPosition);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + targetPosition);
        backRight.setTargetPosition(backRight.getCurrentPosition() + targetPosition);

        setRunToPosition();

        setPower(speed);

        while (opModeIsActive() && areMotorsBusy()) {
            telemetry.addData("Target", targetPosition);
            telemetry.addData("Current Front Left", frontLeft.getCurrentPosition());
            telemetry.update();
        }
        stopMotors();
        resetEncoders();
    }

    private void strafeInches(double inches, double speed) {
        int targetPosition = (int) (inches * COUNTS_PER_INCH);

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - targetPosition);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + targetPosition);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + targetPosition);
        backRight.setTargetPosition(backRight.getCurrentPosition() - targetPosition);

        setRunToPosition();

        setPower(speed);

        while (opModeIsActive() && areMotorsBusy()) {
            telemetry.addData("Target", targetPosition);
            telemetry.addData("Current Front Left", frontLeft.getCurrentPosition());
            telemetry.update();
        }

        stopMotors();
        resetEncoders();
    }
    private void turnDegrees(double degrees, double speed) {
        // Conversion: degrees to encoder ticks
        double TURN_DIAMETER_INCHES = 18.0; // Adjust based on robot's diameter
        double COUNTS_PER_DEGREE = (TURN_DIAMETER_INCHES * Math.PI * COUNTS_PER_INCH) / 360.0;
        int targetPosition = (int) (degrees * COUNTS_PER_DEGREE);

        // Set target positions for turning
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - targetPosition);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + targetPosition);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() - targetPosition);
        backRight.setTargetPosition(backRight.getCurrentPosition() + targetPosition);

        // Set motors to RUN_TO_POSITION mode
        setRunToPosition();

        // Apply power
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);

        // Wait until motors reach their targets
        while (opModeIsActive() && areMotorsBusy()) {
            telemetry.addData("Turning", "Degrees: %f", degrees);
            telemetry.update();
        }

        // Stop motors and reset encoders
        stopMotors();
        resetEncoders();
    }
    public void Coleta1(){
        linkageLeft.setPosition(0.35);
        linkageRight.setPosition(0.6);
        sleep(100);
        pulsoRight.setPosition(0.1);
        pulsoLeft.setPosition(0.75);
        garra.setPosition(0.5);
    }
    public void Coleta2(){
        sleep(500);
        garra.setPosition(0.8);
        sleep(200);
        pulsoRight.setPosition(0.9);
        pulsoLeft.setPosition(0);
        sleep(500);
        linkageRight.setPosition(1);
        linkageLeft.setPosition(0);
        sleep(800);
        garrinha.setPosition(1);
        sleep(400);
        garra.setPosition(0.8);
    }
    public void Entrega(){
        newTarget = ticks / 1;
        polia.setTargetPosition((int) newTarget);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);
        bracinho.setPosition(0.85);
        sleep(800);
        garrinha.setPosition(0.7);
        sleep(200);
        bracinho.setPosition(0.21);
        polia.setTargetPosition(0);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1400);
        polia.setPower(0);
    }
    public void Clip(){
        bracinho.setPosition(1);
        newTarget = ticks3 / 1;
        polia.setTargetPosition((int) newTarget);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void Clipar(){
        newTarget = ticks2 / 2;
        polia.setTargetPosition((int) newTarget);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(600);
        garrinha.setPosition(0.6);
        bracinho.setPosition(0.21);
        sleep(200);
        polia.setTargetPosition(0);
        polia.setPower(1);
        polia.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    private void strafeAndTurn(double strafeInches, double turnDegrees, double speed) {
        // Conversion factors
        double TURN_DIAMETER_INCHES = 18.0; // Adjust for your robot's turning circle
        double COUNTS_PER_DEGREE = (TURN_DIAMETER_INCHES * Math.PI * COUNTS_PER_INCH) / 360.0;
        double strafeCounts = strafeInches * COUNTS_PER_INCH;
        double turnCounts = turnDegrees * COUNTS_PER_DEGREE;

        // Compute motor target positions
        int frontLeftTarget = frontLeft.getCurrentPosition() + (int) (strafeCounts - turnCounts);
        int frontRightTarget = frontRight.getCurrentPosition() + (int) (-strafeCounts + turnCounts);
        int backLeftTarget = backLeft.getCurrentPosition() + (int) (-strafeCounts - turnCounts);
        int backRightTarget = backRight.getCurrentPosition() + (int) (strafeCounts + turnCounts);

        // Set target positions
        frontLeft.setTargetPosition(frontLeftTarget);
        frontRight.setTargetPosition(frontRightTarget);
        backLeft.setTargetPosition(backLeftTarget);
        backRight.setTargetPosition(backRightTarget);

        // Set motors to RUN_TO_POSITION mode
        setRunToPosition();

        // Apply power
        setPower(speed);

        // Wait until all motors reach their targets
        while (opModeIsActive() && areMotorsBusy()) {
            telemetry.addData("Strafing and Turning", "Strafe Inches: %f, Turn Degrees: %f", strafeInches, turnDegrees);
            telemetry.update();
        }
        // Stop motors and reset encoders
        stopMotors();
        resetEncoders();
    }
    private void setRunToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void setPower(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    private boolean areMotorsBusy() {
        return frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy();
    }

    private void stopMotors() {
        setPower(0);
    }

    private void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
