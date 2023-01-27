public class Hornet extends Insect{
    private int attackDamage;

    public Hornet(Tile position, int hp, int attackDamage){
        super(position, hp);
        this.attackDamage= attackDamage;

    }

    public boolean takeAction() {
        boolean bool = false;
        if (this.getPosition() != null) {
            if (this.getPosition().isHive()) //if the hornet is on the hive it cannot take action
                return bool;

            else if (this.getPosition().getBee() != null) { //if there is a bee on the tile sting it
                    this.getPosition().getBee().takeDamage(this.attackDamage);
                    bool = true;
                }
            else {
                Tile newPosition = this.getPosition().towardTheHive();//otherwise move towards the hive
                this.getPosition().removeInsect(this);
                this.setPosition(newPosition);
                this.getPosition().addInsect(this);
                bool = true;
            }
        }return bool;
    }


    @Override
    public boolean equals(Object hornet) {
        if(super.equals(hornet) && this.attackDamage==((Hornet) hornet).attackDamage){
            return true;
        }return false;
    }
}
