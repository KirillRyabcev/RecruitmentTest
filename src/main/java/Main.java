import entity.*;
import exception.CompetenceScaleException;
import exception.EmptyCandidateListexception;
import exception.ObligatoryCompetenceException;
import logic.Recruitment;
import validation.CompetenceValidator;

import java.util.*;

public class Main {
    public static void main(String[] args) throws EmptyCandidateListexception, CompetenceScaleException {

        List<Vacancy> allvacancies = new ArrayList<>();
        List<Candidate> allcandidates = new ArrayList<>();

        List<Candidate> availablecandidates = new ArrayList<>(); //доступные
        HashSet<Candidate> suitablecandidates = new HashSet<>(); //подходящие по компетенциям
        List<Candidate> chosencandidates = new ArrayList<>(); //приглащенные на работу


        CompetenceValidator validator = new CompetenceValidator();
        int level=1;

        try {
            //create vacancy1 start
            List<Competence> req1 = new ArrayList<>();

            Competence comp1 = new Competence();
            comp1.setName(CompetenceNameType.TeamWork);//enter
            comp1.setPriority(CompetencePriorityType.OPTIONAL);//enter
            level = 5;//enter

                if (validator.checkLevel(level)) {
                    comp1.setLevel(level);
                }

            req1.add(comp1);

            Competence comp2 = new Competence();
            comp2.setName(CompetenceNameType.Management);//enter
            comp2.setPriority(CompetencePriorityType.OBLIGATORY);//enter
            level = 7;//enter

                if (validator.checkLevel(level)) {
                    comp2.setLevel(level);
                }

            req1.add(comp2);


                if (validator.checkObligatory(req1)) {
                    System.out.println("ok: vacancy has obligatory priority");
                }


            Vacancy vacancy1 = new Vacancy("Project Manager", 1, req1);//enter
            System.out.println(vacancy1);
            allvacancies.add(vacancy1);
            //create vacancy1 end

            //create candidate1 start
            List<Competence> creq1 = new ArrayList<>();

            Competence ccomp11 = new Competence();
            ccomp11.setName(CompetenceNameType.TeamWork); //enter
            level = 8; //enter

                if (validator.checkLevel(level)) {
                    ccomp11.setLevel(level);
                }

            creq1.add(ccomp11);

            Competence ccomp12 = new Competence();
            ccomp12.setName(CompetenceNameType.Management);//enter
            level = 6;//enter

                if (validator.checkLevel(level)) {
                    ccomp12.setLevel(level);
                }

            creq1.add(ccomp12);

            Candidate candidate1 = new Candidate("Jones", creq1);//enter
            System.out.println(candidate1);
            allcandidates.add(candidate1);
            //create candidate1 end

            //create candidate2 start
            List<Competence> creq2 = new ArrayList<>();

            Competence ccomp21 = new Competence();
            ccomp21.setName(CompetenceNameType.TeamWork); //enter
            level = 3;//enter

                if (validator.checkLevel(level)) {
                    ccomp21.setLevel(level);
                }

            creq2.add(ccomp21);

            Competence ccomp22 = new Competence();
            ccomp22.setName(CompetenceNameType.Management);//enter
            level = 3;//enter

                if (validator.checkLevel(level)) {
                    ccomp22.setLevel(level);
                }

            creq2.add(ccomp22);

            Candidate candidate2 = new Candidate("Brown", creq2);//enter
            System.out.println(candidate2);
            allcandidates.add(candidate2);
            //create candidate2 end

            //create candidate3 start
            List<Competence> creq3 = new ArrayList<>();

            Competence ccomp31 = new Competence();
            ccomp31.setName(CompetenceNameType.DecisionMaking); //enter
            level = 9;//enter

                if (validator.checkLevel(level)) {
                    ccomp31.setLevel(level);
                }

            creq3.add(ccomp31);

            Competence ccomp32 = new Competence();
            ccomp32.setName(CompetenceNameType.Management);//enter
            level = 8;//enter

                if (validator.checkLevel(level)) {
                    ccomp32.setLevel(level);
                }

            creq3.add(ccomp32);

            Candidate candidate3 = new Candidate("Smith", creq3);//enter
            System.out.println(candidate3);
            allcandidates.add(candidate3);
            //create candidate3 end

            //create candidate4 start
            List<Competence> creq4 = new ArrayList<>();

            Competence ccomp41 = new Competence();
            ccomp41.setName(CompetenceNameType.IdeasGeneration); //enter
            level = 10;//enter

                if (validator.checkLevel(level)) {
                    ccomp41.setLevel(level);
                }

            creq4.add(ccomp41);

            Candidate candidate4 = new Candidate("Parker", creq4);//enter
            System.out.println(candidate4);
            allcandidates.add(candidate4);
            //create candidate3 end


            System.out.println("all candidates: " + allcandidates.size() + " : " + allcandidates);

            Recruitment rec = new Recruitment();


            suitablecandidates = rec.matchCompetence(vacancy1, allcandidates);

            System.out.println("Candidates suitable for vacancy: " + suitablecandidates.size() + " : " + suitablecandidates);

            List<Candidate> suitablecandidateslist = new ArrayList<>(suitablecandidates);
            Map<Candidate, Double> goodcoeffcandidates = new TreeMap<>();
            goodcoeffcandidates = rec.calculate(vacancy1, suitablecandidateslist); //вызов метода расчета формулы
            System.out.println("Candidates with coefficient >50%: " + goodcoeffcandidates.size() + " : " + goodcoeffcandidates);


            chosencandidates = rec.hire(goodcoeffcandidates, vacancy1);

            for (int i = 0; i < chosencandidates.size(); i++) {
                for (int j = 0; j < allcandidates.size(); j++) {
                    if (!chosencandidates.get(i).equals(allcandidates.get(j))) {
                        availablecandidates.add(allcandidates.get(j));
                    }
                }
            }
            System.out.println("Available candidates: " + availablecandidates.size() + " : " + availablecandidates);


        }
        catch (EmptyCandidateListexception e) {
            e.printStackTrace();
        }
        catch (CompetenceScaleException e) {
            e.printStackTrace();
        }
        catch (ObligatoryCompetenceException e){
            e.printStackTrace();
        }
    }
}
