import java.util.Objects;

@SuppressWarnings("StatementWithEmptyBody")
public class DoodleBug extends Organism {
    private int turnsAlive;
    private boolean turnPassed;
    private int turnsHungry;


    public DoodleBug(World world, int x, int y) {
        super(world, x, y);
        this.turnsAlive = 0;
        this.turnPassed = false;
        this.turnsHungry = 0;


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

    private void setTurnsHungry() {
        this.turnsHungry += 1;
    }

    private void checkMove() {

        if (eatAnt(x + 1, y)) {
        } else if (eatAnt(x - 1, y)) {
        } else if (eatAnt(x, y + 1)) {
        } else if (eatAnt(x, y - 1)) {
        } else {
            //doodle starts starving
            setTurnsHungry();
            super.move();
        }
    }


    private boolean eatAnt(int maybeX, int maybeY) {
        Organism temp = world.getOrganism(maybeX, maybeY);
        if (world.pointInGrid(maybeX, maybeY) && temp != null && Objects.equals(temp.toString(), "o")) {
            turnsHungry = 0;
            world.addOrganism(this.x, this.y, null);
            world.addOrganism(maybeX, maybeY, this);
            x = maybeX;
            y = maybeY;
            return true;
        }

        return false;
    }

    private void checkSurrounding() {


        if (world.pointInGrid(x + 1, y) && world.getOrganism(x + 1, y) == null) {


            world.addOrganism(x + 1, y, new DoodleBug(world, x + 1, y));

            world.addOrganism(this.x, this.y, this);

            resetTurnsAlive();

        } else if (world.pointInGrid(x - 1, y) && world.getOrganism(x - 1, y) == null) {


            world.addOrganism(x - 1, y, new DoodleBug(world, x - 1, y));

            world.addOrganism(this.x, this.y, this);
            resetTurnsAlive();

        } else if (world.pointInGrid(x, y + 1) && world.getOrganism(x, y + 1) == null) {


            world.addOrganism(x, y + 1, new DoodleBug(world, x, y + 1));

            world.addOrganism(this.x, this.y, this);
            resetTurnsAlive();

        } else if (world.pointInGrid(x, y - 1) && world.getOrganism(x, y - 1) == null) {


            world.addOrganism(x, y - 1, new DoodleBug(world, x, y - 1));

            world.addOrganism(this.x, this.y, this);

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
            checkMove();

            setTurnsAlive();

        }

    }


   public void starve() {

      if (turnsHungry >= 3) {
         world.addOrganism(this.x, this.y, null);
      }

    }



    @Override
    public void reproduce() {
        if (turnsAlive >= 8) {
            checkSurrounding();

        }

    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public void turnReset() {
        setTurnPassed(false);
    }


}
