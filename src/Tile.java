public class Tile {
    private int foodOnTile;
    private boolean beeHive;
    private boolean hornetNest;
    private boolean nestToHivePath;
    private Tile nextTileNestToHive;
    private Tile nextTileHiveToNest;
    private HoneyBee beeOnTile;
    private SwarmOfHornets hornetsOnTile;

    public Tile() { //constructor
        this.foodOnTile = 0;
        this.beeHive = false;
        this.hornetNest = false;
        this.nestToHivePath = false;
        this.nextTileNestToHive = null;
        this.nextTileHiveToNest = null;
        this.beeOnTile = null;
        this.hornetsOnTile = new SwarmOfHornets();
    }

    public Tile(int foodOnTile, boolean beeHive, boolean hornetNest, boolean nestToHivePath, Tile nextTileNestToHive,
                Tile nextTileHiveToNest, HoneyBee beeOnTile, SwarmOfHornets hornetsOnTile) { //constructor
        this.foodOnTile = foodOnTile;
        this.beeHive = beeHive;
        this.hornetNest = hornetNest;
        this.nestToHivePath = nestToHivePath;
        this.nextTileNestToHive = nextTileNestToHive;
        this.nextTileHiveToNest = nextTileHiveToNest;
        this.beeOnTile = beeOnTile;
        this.hornetsOnTile = hornetsOnTile;
    }

    public boolean isHive() {
        return this.beeHive;
    }

    public boolean isNest() {
        return this.hornetNest;
    }

    public void buildHive() {
        this.beeHive = true;
    }

    public void buildNest() {
        this.hornetNest = true;
    }

    public boolean isOnThePath() {
        return this.nestToHivePath;
    }

    public Tile towardTheHive() {
        return this.nextTileNestToHive;
    }

    public Tile towardTheNest() {
        return this.nextTileHiveToNest;
    }

    public void createPath(Tile towardsHive, Tile towardsNest) {
        this.nextTileNestToHive = towardsHive;
        if (towardsHive!=null)
        towardsHive.nextTileHiveToNest= this; // the next tile to the nest from towardsHive is this tile
        this.nextTileHiveToNest = towardsNest;
        if(towardsNest!=null)
        towardsNest.nextTileNestToHive= this; // the next tile to the hive from towardsNest is this
        this.nestToHivePath = true;
    }

    public int collectFood() {
        int food = this.foodOnTile;
        this.foodOnTile = 0;
        return food;
    }

    public void storeFood(int food) {
        this.foodOnTile = this.foodOnTile + food;
    }

    public HoneyBee getBee() {
        return this.beeOnTile;
    }

    public Hornet getHornet() {

        return this.hornetsOnTile.getHornets()[0];
    }

    public int getNumOfHornets() {
        if(this.hornetsOnTile==null)
            return 0;
        return this.hornetsOnTile.sizeOfSwarm();
    }

    public boolean addInsect(Insect insect) {
        boolean insectAdded = false;
        if (insect instanceof HoneyBee) { //if the insect is a bee
            if (this.getBee() == null && this.isNest()== false) { //if there is no bee or hornet nest on the tile
                this.beeOnTile = (HoneyBee) insect;
                insect.setPosition(this);
                insectAdded = true;
            }
        }
        if(insect instanceof Hornet){ //if the insect is a hornet
            if(this.isNest()==true || this.isHive() || this.isOnThePath()==true){ //if there is a hive or nest on the tile or if the tile is on the path
                this.hornetsOnTile.addHornet((Hornet) insect);
                insect.setPosition(this);
                insectAdded= true;
            }

        }return insectAdded;
    }
    public boolean removeInsect(Insect insect){
        boolean insectRemoved= false;
        if(insect instanceof HoneyBee){
            if(this.getBee()!=null){  //if there is a bee on the tile
                this.beeOnTile=null; //remove it from the tile
                insect.setPosition(null);
                insectRemoved=true;
            }
        }
        if(insect instanceof Hornet){
            if(this.hornetsOnTile!=null){ //if there is a hornet on the tile
                this.hornetsOnTile.removeHornet((Hornet) insect); //remove it from the swarm
                insectRemoved=true;
            }
        }return insectRemoved;

    }
}
