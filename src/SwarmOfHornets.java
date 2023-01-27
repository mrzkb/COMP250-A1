
public class SwarmOfHornets {
    private Hornet[] Hornets;
    private int swarmSize;

    public SwarmOfHornets() {
        this.Hornets = new Hornet[0];
        this.swarmSize = 0;
    }

    public int sizeOfSwarm() {
        return this.swarmSize;
    }

    public Hornet[] getHornets() {
        return this.Hornets;
    }

    public Hornet getFirstHornet() {
        if (this.swarmSize == 0) {
            return null;
        }
        return Hornets[0];
    }

    public void addHornet(Hornet newHornet) {
        Hornet[] newHornetsArray = new Hornet[this.sizeOfSwarm() + 1];
        if (this.sizeOfSwarm() == 0) { //if there are no hornets in the array add the new hornet
            newHornetsArray[0] = newHornet;
            this.Hornets = newHornetsArray;
            this.swarmSize = this.sizeOfSwarm() + 1;
        } else {
            for (int i = 0; i < this.sizeOfSwarm(); i++) { //add all the existing hornets to the new array
                newHornetsArray[i] = this.Hornets[i];
            }
            newHornetsArray[this.sizeOfSwarm()] = newHornet; //add the new hornet to the end

            this.swarmSize = this.sizeOfSwarm() + 1;
            this.Hornets = newHornetsArray; //hornets become all the hornets in the new array
        }
    }

    public boolean removeHornet(Hornet removedHornet) {
        boolean bool= false;
        int index=0;
        if(this.sizeOfSwarm()==0) //if the swarm is empty return false
            return bool;
        Hornet[] removedHornetArray = new Hornet[this.sizeOfSwarm() - 1];

        for(int i=0; i<this.sizeOfSwarm(); i++){
            if(this.Hornets[i]==removedHornet && bool==false){ //if the removed hornet is at index i and a hornet has not already been removed
                bool= true;
                continue;
            }
            else{
                if(index==this.sizeOfSwarm()-1)
                    break;

                removedHornetArray[index]=this.Hornets[i];
                index++;
            }
        }
        if(bool==false)
            return bool;

        this.Hornets= removedHornetArray;
        this.swarmSize= this.sizeOfSwarm()-1;
        return bool;
    }
}

