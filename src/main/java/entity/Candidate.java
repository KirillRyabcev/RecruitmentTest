package entity;

import java.util.List;

public class Candidate {

    private String surname;
    private List<Competence> candidateCompetence;

    public Candidate(String surname, List<Competence> candidateCompetence) throws IllegalArgumentException{
        this.surname = surname;
        if(surname == "") throw new IllegalArgumentException("пустая строка!!!");
        this.candidateCompetence = candidateCompetence;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws IllegalArgumentException{
        if(surname == "") throw new IllegalArgumentException("Пустая строка!!!");
        this.surname = surname;
    }

    public List<Competence> getCandidateCompetence() {
        return candidateCompetence;
    }

    public void setCandidateCompetence(List<Competence> candidateCompetence) {
        this.candidateCompetence = candidateCompetence;
    }

    @Override
    public String toString() {
        return "entity.Candidate{" +
                "surname='" + surname + '\'' +
                ", candidateCompetence=" + candidateCompetence +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        if (surname != null ? !surname.equals(candidate.surname) : candidate.surname != null) return false;
        return candidateCompetence != null ? candidateCompetence.equals(candidate.candidateCompetence) : candidate.candidateCompetence == null;
    }

    @Override
    public int hashCode() {
        int result = surname != null ? surname.hashCode() : 0;
        result = 31 * result + (candidateCompetence != null ? candidateCompetence.hashCode() : 0);
        return result;
    }
}
