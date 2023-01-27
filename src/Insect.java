public abstract class Insect {
    private Tile position;
    private int hp;

    public Insect(Tile position, int hp){
        if(position.getBee()!=null && this instanceof HoneyBee){ //if there is a bee on the tile already
            throw new IllegalArgumentException();}
        this.position= position;
        this.hp= hp;
        this.position.addInsect(this);
    }

    public final Tile getPosition(){
        return this.position;
    }

    public final int getHealth(){
        return this.hp;
    }

    public void setPosition(Tile setPosition){
        this.position= setPosition;
    }

    public void takeDamage(int damage) {
        if (this instanceof HoneyBee) {
            if (this.position.isHive()) { //if the insect is a bee on the hive
                int newDamage = (int) (0.9 * damage); //reduce the damage
                this.hp = this.hp - newDamage;
            }
            else{
                this.hp=this.hp-damage;
            }
        }
        else {
            this.hp= this.hp-damage;
        }
        if(this.hp<=0) //if the insect has been killed
            this.position.removeInsect(this);
        }
        public abstract boolean takeAction();

    @Override
    public boolean equals(Object insect){
        if(insect==null || this.getClass()!= insect.getClass())
            return false;

        if(this.hp==((Insect) insect).hp && this.position== ((Insect) insect).position)
            return true;
        else
            return false;
    }

    public static void main(String[] args){
        Tile Tile1= new Tile();
        TankyBee bee= new TankyBee(Tile1,5,5);

        Hornet hornet= new Hornet(Tile1,50, 5);

    }

    }

