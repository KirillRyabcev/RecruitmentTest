package entity;

import exception.ObligatoryCompetenceException;

import java.util.List;

public class Vacancy {

    private String name;
    private int candidatesQuantity;
    private List<Competence> requiredCompetence;

    public Vacancy(String name, int candidatesQuantity, List<Competence> requiredCompetence) throws IllegalArgumentException{
        this.name = name;
        if(name == "") throw new IllegalArgumentException("пустое имя вакансии!!!");
        this.candidatesQuantity = candidatesQuantity;
        this.requiredCompetence = requiredCompetence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException{
        if(name == "") throw new IllegalArgumentException("пустая строка названия!!!");
        this.name = name;
    }

    public int getCandidatesQuantity() {
        return candidatesQuantity;
    }

    public void setCandidatesQuantity(int candidatesQuantity) throws IllegalArgumentException {
        if(candidatesQuantity < 1) throw new IllegalArgumentException("Количество кандидатов меньше 1!!!");
        this.candidatesQuantity = candidatesQuantity;
    }

    public List<Competence> getRequiredCompetence() {
        return requiredCompetence;
    }

    public void setRequiredCompetence(List<Competence> requiredCompetence) throws ObligatoryCompetenceException{
        this.requiredCompetence = requiredCompetence;
    }

    @Override
    public String toString() {
        return "entity.Vacancy{" +
                "name='" + name + '\'' +
                ", candidatesQuantity=" + candidatesQuantity +
                ", requiredCompetence=" + requiredCompetence +
                '}';
    }
}
