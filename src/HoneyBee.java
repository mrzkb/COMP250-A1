public abstract class HoneyBee extends Insect{
    private int foodCost;

    public HoneyBee(Tile position, int hp, int foodCost){
        super(position, hp);
        this.foodCost= foodCost;
    }

    public int getCost(){
        return this.foodCost;
    }

    @Override
    public boolean equals(Object honeyBee) {
        if(super.equals(honeyBee) && this.getCost()==((HoneyBee) honeyBee).getCost()){
            return true;
        }return false;
    }
}
