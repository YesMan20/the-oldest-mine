package net.clockwork.the_oldest_mine.server.entities;

public class AgressiveBehaviour extends Behaviour {

    public AgressiveBehaviour(GiantEntity entity) {
        super(entity);
    }

    @Override
    public void onAttach() {
        this.entity.goalSelector.addGoal(1, this.entity.attackGoal);
        this.entity.goalSelector.removeGoal(this.entity.slowlyChaseGoal);
    }


    @Override
    public void onDetach() {
        this.entity.goalSelector.removeGoal(this.entity.attackGoal);
    }

}
