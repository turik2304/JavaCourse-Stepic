import java.util.Scanner;

public class Robot {
    public static int x = 0;
    public static int toX = 0;
    public static int y = 0;
    public static int toY = 0;
    public static String dir = "UP";
    public static String command = "";
    public static Scanner enterCommandScanner;
    public static Robot robot;

    public static void main(String[] args) {

        //ENTER COMMAND
        System.out.println("COMMANDS:\n" +
                "inSt - initial state\n" +
                "endSt - end state\n" +
                "getDir - get direction\n" +
                "getX - get X coordinate\n" +
                "getY - get Y coordinate\n" +
                "turnLeft - 90 degree left\n" +
                "step - step in to direction\n" +
                "---ENTER COMMAND---");

        enterCommandScanner = new Scanner(System.in);
        do {
            command = enterCommandScanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            } else if (command.equalsIgnoreCase("inSt")) {
                initialState();
            } else if (command.equalsIgnoreCase("endSt")) {
                endState();
            } else if (command.equalsIgnoreCase("move")) {
                robot.moveRobot(robot, toX, toY);
            } else if (command.equalsIgnoreCase("getDir")) {
                getDirection();
            } else if (command.equalsIgnoreCase("getX")) {
                getX();
            } else if (command.equalsIgnoreCase("getY")) {
                getY();
            } else if (command.equalsIgnoreCase("turnLeft")) {
                turnLeft();
            }  else if (command.equalsIgnoreCase("step")) {
                stepForward();
            } else {
                System.out.println("WRONG!");
                System.out.print("ENTER COMMAND: ");
            }
        } while (!command.equalsIgnoreCase("exit"));
    }

    public static void initialState() {
        System.out.println("---Initial state(x,y,direction)---");
        System.out.print("x = ");
        x = enterCommandScanner.nextInt();
        System.out.print("y = ");
        y = enterCommandScanner.nextInt();
        enterCommandScanner.nextLine(); //because .nextInt produce \n(Enter), which is unnecessary
        System.out.print("Direction: ");
        dir = enterCommandScanner.nextLine();
        System.out.println("Initial state (" + x + ", " + y + ", " + dir + ")");
        System.out.print("ENTER COMMAND: ");
    }

    public static void endState() {
        System.out.println("---End state(x,y)---");
        System.out.print("x = ");
        toX = enterCommandScanner.nextInt();
        System.out.print("y = ");
        toY = enterCommandScanner.nextInt();
        System.out.println("End state (" + toX + ", " + toY + ")");
        System.out.print("ENTER COMMAND: ");
    }

    public void moveRobot(Robot robot, int toX, int toY) {
        if ((toX - robot.getX()) > 0) {
            while (robot.getDirection() != "Direction.LEFT") {
                robot.turnLeft();
            }
            while (robot.getX() != toX) {
                robot.stepForward();
            }
        }
        if ((toX - robot.getX()) < 0) {
            while (robot.getDirection() != "Direction.RIGHT") {
                robot.turnLeft();
            }
            while (robot.getX() != toX) {
                robot.stepForward();
            }
        }
        if ((toY - robot.getY()) > 0) {
            while (robot.getDirection() != "Direction.UP") {
                robot.turnLeft();
            }
            while (robot.getY() != toY) {
                robot.stepForward();
            }
        }
        if ((toY - robot.getX()) < 0) {
            while (robot.getDirection() != "Direction.DOWN") {
                robot.turnLeft();
            }
            while (robot.getY() != toY) {
                robot.stepForward();
            }
        }

    }

/*
        public enum Direction {
            UP,
            DOWN,
            LEFT,
            RIGHT
        }
*/


/*
    public void setDirection() {
        //установка направления
        System.out.println("---Set direction---");
        dir = enterCommandScanner.nextLine();
        if (dir.equalsIgnoreCase("UP"))
            dir = Direction.UP.toString();
        System.out.print("Okay, current direction is " + dir);
        System.out.print("ENTER COMMAND: ");
    }
*/

    public static String getDirection() {
        //текущее направление
        System.out.println("Current direction is " + dir);
        System.out.print("ENTER COMMAND: ");
        return dir;

    }

    public static int getX() {
        // текущая координата X
        System.out.println("x = " + x);
        return x;
    }

    public static int getY() {
        // текущая координата Y
        System.out.println("y = " + y);
        return y;
    }

    public static String turnLeft() {
        // повернуться на 90 градусов против часовой стрелки
        if (dir.equalsIgnoreCase("UP")) {
            dir = "LEFT";
            System.out.println("Current state (" + x + ", " + y + ", " + dir + ")");
        } else if (dir.equalsIgnoreCase("LEFT")) {
            dir = "DOWN";
            System.out.println("Current state (" + x + ", " + y + ", " + dir + ")");
        } else if (dir.equalsIgnoreCase("DOWN")) {
            dir = "RIGHT";
            System.out.println("Current state (" + x + ", " + y + ", " + dir + ")");
        } else if (dir.equalsIgnoreCase("RIGHT")) {
            dir = "UP";
            System.out.println("Current state (" + x + ", " + y + ", " + dir + ")");
        }
        return dir;
    }

    public void turnRight() {
        // повернуться на 90 градусов по часовой стрелке
    }

    public static void stepForward() {
        // шаг в направлении взгляда
        // за один шаг робот изменяет одну свою координату на единицу
        if (dir.equalsIgnoreCase("UP")) {
            y+=1;
            System.out.println("Current state (" + x + ", " + y + ", " + dir + ")");
        } else if (dir.equalsIgnoreCase("LEFT")) {
            x-=1;
            System.out.println("Current state (" + x + ", " + y + ", " + dir + ")");
        } else if (dir.equalsIgnoreCase("DOWN")) {
            y-=1;
            System.out.println("Current state (" + x + ", " + y + ", " + dir + ")");
        } else if (dir.equalsIgnoreCase("RIGHT")) {
            x+=1;
            System.out.println("Current state (" + x + ", " + y + ", " + dir + ")");
        }
    }
}

