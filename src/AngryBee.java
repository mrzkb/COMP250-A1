public class AngryBee extends HoneyBee{

    private int attackDamage;

    public AngryBee(Tile position, int attackDamage){
        super(position,10, 1);
        this.attackDamage= attackDamage;
    }

    public boolean takeAction(){ //something could be wrong with this
        boolean sting= false;
        if(this.getPosition().isOnThePath()==true || this.getPosition().isHive()==true){ //if the bee is on the path or hive
            Tile originalPosition= this.getPosition();
            while(this.getPosition().getNumOfHornets()==0 && !this.getPosition().isNest()){ //keep moving until it finds a non-empty swarm or hornet nest
                this.setPosition(this.getPosition().towardTheNest());
            }
            if(this.getPosition().isNest()){ //if the tile is the nest retreat
                this.setPosition(originalPosition);
                return sting;}

            if(this.getPosition().getNumOfHornets()!=0){ //if there is an non empty swarm sting the first hornet
                Hornet stungHornet= this.getPosition().getHornet();
                stungHornet.takeDamage(this.attackDamage);
                this.setPosition(originalPosition);
                sting=true;
            }

        } return sting;
    }

    @Override
    public boolean equals(Object angryBee) {
        if(super.equals(angryBee) && this.attackDamage==((AngryBee) angryBee).attackDamage){
            return true;
        }return false;
    }
}