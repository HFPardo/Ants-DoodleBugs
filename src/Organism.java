

public abstract class Organism {

    World world;
    int x;
    int y;


    Organism() {


    }


    Organism(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;


    }


    void move() {


        int moveX = x + ((int) (Math.random() * 3) - 1);
        int moveY = y + ((int) (Math.random() * 3) - 1);


        if (world.pointInGrid(moveX, moveY) && world.getOrganism(moveX, moveY) == null) {

            world.addOrganism(moveX, moveY, this);
            world.addOrganism(this.x, this.y, null);

            x = moveX;
            y = moveY;

        }

    }


    public abstract void starve();

    public abstract void turn();

    public abstract void turnReset();

    public abstract void reproduce();

    public abstract String toString();


}
