/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badgerbots.demobot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Finn
 */
public class CalibrateGyro extends CommandBase {
    
    public CalibrateGyro() {
        requires(drivetrain);
        setInterruptible(false);
        setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(1.0);
        drivetrain.stop();
        Timer.delay(0.5);
        drivetrain.resetGyro();
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
