package KarelProgram;

public abstract class SuperKarel extends Karel {

    protected void turnRight() {
        techCurrentDirection = (techCurrentDirection + 3)%4;
        toDraw.add("TurnRight");
    }

    protected void turnAround() {
        techCurrentDirection = (techCurrentDirection + 2)%4;
        toDraw.add("TurnAround");
    }
}
