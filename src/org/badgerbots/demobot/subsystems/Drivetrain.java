package org.badgerbots.demobot.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.badgerbots.demobot.PIDRobotDrive;
import org.badgerbots.demobot.RobotMap;
import org.badgerbots.demobot.commands.DriveWithControls;

/**
 * @author Finn Voichick
 */
public class Drivetrain extends Subsystem {

    private final PIDRobotDrive drive;
    private final Gyro gyro;
    private final PIDController pid;

    public Drivetrain() {
        super();

        drive = new PIDRobotDrive(RobotMap.FRONT_LEFT_MOTOR, RobotMap.REAR_LEFT_MOTOR, RobotMap.FRONT_RIGHT_MOTOR, RobotMap.REAR_RIGHT_MOTOR);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

        gyro = new Gyro(RobotMap.GYRO);

        pid = new PIDController(1.0, 0.0, 0.0, gyro, drive);
        pid.setContinuous();
        pid.setInputRange(0.0, 360.0);
        pid.setOutputRange(-1.0, 1.0);

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithControls());
    }

    /**
     * Drives the robot
     *
     * @param x The X-translation vector
     * @param y The Y-translation vector
     * @param rotation The robot's rotation. If in PID mode, this should be a
     * new setpoint (the direction that the robot should face).
     *
     */
    public void drive(double x, double y, double rotation) {
        if (SmartDashboard.getBoolean("Safety Mode")) {
            drive.setMaxOutput(0.2);
        } else {
            drive.setMaxOutput(1.0);
        }
        double gyroAngle = gyro.pidGet();
        SmartDashboard.putNumber("Gyro Angle", gyroAngle);
        SmartDashboard.putNumber("Speed", Math.sqrt(x * x + y * y));
        if (SmartDashboard.getBoolean("PIDControl")) {
            pid.setSetpoint(rotation);
            drive.mecanumDrive_Cartesian_PID(x, y, gyroAngle);
        } else {
            drive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
        }
    }
    
    public void stop() {
        drive.mecanumDrive_Cartesian(0.0, 0.0, 0.0, 0.0);
    }
    
    public void resetGyro() {
        gyro.reset();
    }

}
