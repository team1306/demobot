/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.badgerbots.demobot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author Finn
 */
public class PIDRobotDrive extends RobotDrive implements PIDOutput{

    public PIDRobotDrive(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
        pidOutput = 0.0;
    }

    public void pidWrite(double output) {
        pidOutput = output;
    }
    
    public void mecanumDrive_Cartesian_PID(double x, double y, double gyroAngle) {
        super.mecanumDrive_Cartesian(x, y, pidOutput, gyroAngle);
    }
    private double pidOutput;
}
