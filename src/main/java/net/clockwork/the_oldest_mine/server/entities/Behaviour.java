package net.clockwork.the_oldest_mine.server.entities;

public abstract class Behaviour {
    protected final GiantEntity entity;

    public Behaviour(GiantEntity entity) {
        this.entity = entity;
    }

    public abstract void onAttach();

    public abstract void onDetach();
}
