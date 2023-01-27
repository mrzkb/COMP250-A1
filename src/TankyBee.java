public class TankyBee extends HoneyBee{
    private int attackDamage;
    private int armor;

    public TankyBee(Tile position, int attackDamage, int armor){
        super(position, 30, 3);
        this.attackDamage= attackDamage;
        this.armor= armor;
    }
    public boolean takeAction() {
        if (this.getPosition() != null) {
            if (this.getPosition().getNumOfHornets() != 0) { //if there is a non-empty swarm sting the first hornet
                Hornet stungHornet = this.getPosition().getHornet();
                stungHornet.takeDamage(this.attackDamage);
                return true;
            }
        }return false;
    }

    public void takeDamage(int damage){
        int newDamage= (int)((100.0/(100.0+this.armor))*damage);
        super.takeDamage(newDamage);
    }

    @Override
    public boolean equals(Object tankyBee) {
        if( super.equals(tankyBee) && this.attackDamage==((TankyBee) tankyBee).attackDamage && this.armor==((TankyBee) tankyBee).armor) {
            return true;
        }return false;
    }
}
