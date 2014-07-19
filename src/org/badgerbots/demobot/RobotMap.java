package org.badgerbots.demobot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 *
 * @author Finn Voichick
 */
public class RobotMap {

    public static final int FRONT_LEFT_MOTOR = 3;
    public static final int REAR_LEFT_MOTOR = 1;
    public static final int FRONT_RIGHT_MOTOR = 4;
    public static final int REAR_RIGHT_MOTOR = 2;

    public static final int LEFT_JOYSTICK = 1;
    public static final int RIGHT_JOYSTICK = 2;
    public static final int XBOX_CONTROLLER = 3;
    
    public static final int GYRO = 1;

    private RobotMap() {
    }
}
