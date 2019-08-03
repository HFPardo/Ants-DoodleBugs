import java.util.Objects;

class World {


    private final Organism[][] organism = new Organism[20][20];
    private int rand = (int) (Math.random() * 20);
    private int rand2 = (int) (Math.random() * 20);
    private int count = 2;


// This Constructor will only be used ONCE to add 100 ants and 5 doodlebugs

    public World(int ants, int doodleBugs) {

        for (int i = 1; i <= ants; i++) {
            if (isCellEmpty(rand, rand2) && pointInGrid(rand, rand2)) {
                addNewOrganismToWorld(new Ant(this, rand, rand2));

            } else {
                rand = (int) (Math.random() * 20);
                rand2 = (int) (Math.random() * 20);
                i--;
            }


        }

        for (int i = 1; i <= doodleBugs; i++) {
            if (isCellEmpty(rand, rand2) && pointInGrid(rand, rand2)) {
                addNewOrganismToWorld(new DoodleBug(this, rand, rand2));

            } else {
                rand = (int) (Math.random() * 20);
                rand2 = (int) (Math.random() * 20);
                i--;
            }


        }

        testView();
    }

    //This method will only be used ONCE to randomly add the initial ants and doodlebugs
    private void addNewOrganismToWorld(Organism organism) {

        this.organism[rand][rand2] = organism;

    }

    /*****************************************END************************************************/

    public void addOrganism(int x, int y, Organism organism) {
        this.organism[x][y] = organism;
    }


    public Organism getOrganism(int x, int y) {
        if (pointInGrid(x, y) && this.organism[x][y] != null) {
            return this.organism[x][y];

        } else {

            return null;
        }
    }

    //Check to see if cell is empty
    private boolean isCellEmpty(int x, int y) {
        return organism[x][y] == null;
    }

    public boolean pointInGrid(int x, int y) {
        return x >= 0 && x < 20 && y >= 0 && y < 20;
    }

    //ASCII world for testing purposes
    private void testView() {

        System.out.printf("%n_____________________________________________________________%n");
        for (Organism[] organisms : organism) {
            for (Organism value : organisms) {
                if (value == null) {
                    System.out.print("|  ");

                } else
                    System.out.printf("| %s", value);
            }
            System.out.printf("| %n_____________________________________________________________%n");

        }

    }


    public void simulate() {


        for (Organism[] j : this.organism) {
            for (Organism k : j) {
                if (k != null && Objects.equals(k.toString(), "X")) {

                    k.move();
                    k.turn();

                }
            }

        }


        for (Organism[] j : this.organism) {
            for (Organism k : j) {
                if (k != null && Objects.equals(k.toString(), "o")) {

                    k.move();
                    k.turn();


                }
            }

        }


        for (Organism[] j : this.organism) {
            for (Organism k : j) {
                if (k != null) {
                    k.turnReset();
                    k.reproduce();
                    k.starve();


                }
            }

        }

        testView();

        System.out.println("Turn " + count + " complete");
        count++;


    }


}



