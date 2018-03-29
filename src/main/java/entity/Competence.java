package entity;

import exception.CompetenceScaleException;

public class Competence {

    private CompetenceNameType name;
    private CompetencePriorityType priority;
    private int level;

    public CompetenceNameType getName() {
        return name;
    }

    public void setName(CompetenceNameType name) {
        this.name = name;
    }

    public CompetencePriorityType getPriority() {
        return priority;
    }

    public void setPriority(CompetencePriorityType priority) {
        this.priority = priority;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Competence() {
    }

    public Competence(CompetenceNameType name, CompetencePriorityType priority, int level) {
        this.name = name;
        this.priority = priority;
        this.level = level;
    }

    @Override
    public String toString() {
        return "entity.Competence{" +
                "name=" + name +
                ", priority=" + priority +
                ", level=" + level +
                '}';
    }
}
