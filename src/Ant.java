public class Ant extends Organism {

    private int turnsAlive;
    private boolean turnPassed;


    public Ant(World world, int x, int y) {
        super(world, x, y);
        this.turnsAlive = 0;
        this.turnPassed = false;
    }


    private void setTurnsAlive() {
        this.turnsAlive += 1;
    }


    private void resetTurnsAlive() {
        this.turnsAlive = 0;
    }


    private void setTurnPassed(boolean turnPassed) {
        this.turnPassed = turnPassed;
    }

    private void checkSurrounding() {


        if (world.pointInGrid(x + 1, y) && world.getOrganism(x + 1, y) == null) {

            world.addOrganism(x + 1, y, new Ant(world, x + 1, y));

            world.addOrganism(x, y, this);
            resetTurnsAlive();
        } else if (world.pointInGrid(x - 1, y) && world.getOrganism(x - 1, y) == null) {

            world.addOrganism(x - 1, y, new Ant(world, x - 1, y));

            world.addOrganism(x, y, this);
            resetTurnsAlive();

        } else if (world.pointInGrid(x, y + 1) && world.getOrganism(x, y + 1) == null) {

            world.addOrganism(x, y + 1, new Ant(world, x, y + 1));

            world.addOrganism(x, y, this);
            resetTurnsAlive();

        } else if (world.pointInGrid(x, y - 1) && world.getOrganism(x, y - 1) == null) {

            world.addOrganism(x, y - 1, new Ant(world, x, y - 1));

            world.addOrganism(x, y, this);
            resetTurnsAlive();

        }

    }


    @Override
    public void turn() {
        setTurnPassed(true);
    }

    @Override
    public void move() {
        if (!turnPassed) {
            super.move();
            setTurnsAlive();


        }
        setTurnsAlive();

    }


    @Override
    public void starve(){};

    @Override
    public void reproduce() {
        if (turnsAlive >= 3) {
            checkSurrounding();

        }

    }

    @Override
    public String toString() {
        return "o";
    }

    @Override
    public void turnReset() {
        setTurnPassed(false);

    }

}
