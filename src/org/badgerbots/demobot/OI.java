package org.badgerbots.demobot;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * @author Finn Voichick
 */
public class OI {

    private final Joystick leftJoy;
    private final Joystick rightJoy;
    private final XboxController xbox;

    private final Button buttonA;
    private final Button buttonB;
    private final Button buttonX;
    private final Button buttonY;
    private final Button buttonLB;
    private final Button buttonRB;
    private final Button buttonBack;
    private final Button buttonStart;
    private final Button buttonLS;
    private final Button buttonRS;

    public double moveX() {
        if (SmartDashboard.getBoolean("Xbox Control")) {
            return -deadband(xbox.getX(GenericHID.Hand.kLeft));
        } else {
            return -deadband(leftJoy.getX());
        }
    }

    public double moveY() {
        if (SmartDashboard.getBoolean("Xbox Control")) {
            return deadband(xbox.getY(GenericHID.Hand.kLeft));
        } else {
            return deadband(leftJoy.getY());
        }
    }

    public double rotation() {
        double x;
        double y;
        if (SmartDashboard.getBoolean("Xbox Control")) {
            x = xbox.getX(GenericHID.Hand.kRight);
            y = xbox.getY(GenericHID.Hand.kRight);
        } else {
            x = rightJoy.getX();
            y = rightJoy.getY();
        }
        if (SmartDashboard.getBoolean("PID Control")) {
            if (Math.sqrt(x * x + y * y) > 0.85) {
                lastDirection = angle(x, y);
            }
            return lastDirection;

        } else {
            return -deadband(x);
        }
    }
    private double lastDirection;

    private double deadband(double original) {
        if (original < -0.15) {
            return (original + 0.15) / 0.85;
        } else if (original < 0.15) {
            return 0.0;
        } else {
            return (original - 0.15) / 0.85;
        }
    }

    private double angle(double x, double y) {
        return Math.toDegrees(MathUtils.atan2(x, y));
    }

    public OI() {
        super();

        leftJoy = new Joystick(RobotMap.LEFT_JOYSTICK);
        rightJoy = new Joystick(RobotMap.RIGHT_JOYSTICK);
        xbox = new XboxController(RobotMap.XBOX_CONTROLLER);

        buttonA = new JoystickButton(xbox, XboxController.A);
        buttonB = new JoystickButton(xbox, XboxController.B);
        buttonX = new JoystickButton(xbox, XboxController.X);
        buttonY = new JoystickButton(xbox, XboxController.Y);
        buttonLB = new JoystickButton(xbox, XboxController.LB);
        buttonRB = new JoystickButton(xbox, XboxController.RB);
        buttonBack = new JoystickButton(xbox, XboxController.BACK);
        buttonStart = new JoystickButton(xbox, XboxController.START);
        buttonLS = new JoystickButton(xbox, XboxController.LS);
        buttonRS = new JoystickButton(xbox, XboxController.RS);

        SmartDashboard.putBoolean("PID Control", false);
        SmartDashboard.putBoolean("Safety Mode", true);
        SmartDashboard.putBoolean("Xbox Control", true);
    }

}
