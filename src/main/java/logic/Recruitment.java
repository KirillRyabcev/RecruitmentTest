package logic;

import entity.Candidate;
import entity.CompetencePriorityType;
import entity.Vacancy;
import exception.EmptyCandidateListexception;

import java.util.*;

public class Recruitment {

    public HashSet<Candidate> matchCompetence(Vacancy vac, List<Candidate> allcandidates) throws EmptyCandidateListexception {
        HashSet<Candidate> suitablecandidates = new HashSet<>();

        if (allcandidates.isEmpty()) {
            throw new EmptyCandidateListexception("Candidate's list is empty!");
        }
        for (int i = 0; i<vac.getRequiredCompetence().size(); i++)
        {
            if (vac.getRequiredCompetence().get(i).getPriority().equals(CompetencePriorityType.OBLIGATORY))
            {

                for (int j=0; j<allcandidates.size();j++){
                    for (int k = 0; k<allcandidates.get(j).getCandidateCompetence().size(); k++) {
                       if( allcandidates.get(j).getCandidateCompetence().get(k).getName().equals(vac.getRequiredCompetence().get(i).getName())){
                           suitablecandidates.add(allcandidates.get(j));
                       }
                    }
                }

            }

        }

        return suitablecandidates;

    }

    public Map<Candidate, Double> calculate (Vacancy vac1, List<Candidate> suitablecandidates1){
        Map<Candidate,Double> goodcoeffcandidates = new HashMap<>();
        int obligatorycounter=0;
        double coeff;
        double optionalcoeff;
        double sum;

        for (int i = 0; i<vac1.getRequiredCompetence().size(); i++) {
            if (vac1.getRequiredCompetence().get(i).getPriority().equals(CompetencePriorityType.OBLIGATORY))

            {
                obligatorycounter++;
            }
        }


        for (int j=0; j<suitablecandidates1.size();j++){
            sum=0;
            optionalcoeff=0;
            coeff=0;
            for (int k = 0; k<suitablecandidates1.get(j).getCandidateCompetence().size(); k++) {

                for (int i = 0; i<vac1.getRequiredCompetence().size(); i++)
                {
                    if (vac1.getRequiredCompetence().get(i).getPriority().equals(CompetencePriorityType.OBLIGATORY))

                    {
                        if( suitablecandidates1.get(j).getCandidateCompetence().get(k).getName().equals(vac1.getRequiredCompetence().get(i).getName()))
                        {
                            sum += (suitablecandidates1.get(j).getCandidateCompetence().get(k).getLevel())/(double)(vac1.getRequiredCompetence().get(i).getLevel());

                        }

                    }
                    if (vac1.getRequiredCompetence().get(i).getPriority().equals(CompetencePriorityType.OPTIONAL))

                    {
                        if( suitablecandidates1.get(j).getCandidateCompetence().get(k).getName().equals(vac1.getRequiredCompetence().get(i).getName()) && suitablecandidates1.get(j).getCandidateCompetence().get(k).getLevel() >= vac1.getRequiredCompetence().get(i).getLevel())
                        {
                            optionalcoeff += 5;
                        }
                    }

                }

            }

            coeff=(sum*100)/obligatorycounter + optionalcoeff;
            System.out.println("calculation..."+suitablecandidates1.get(j).getSurname() );
            System.out.println(sum);
            System.out.println(obligatorycounter);
            System.out.println(optionalcoeff);
            System.out.println(coeff+"\n");

            if (coeff>50){
                goodcoeffcandidates.put(suitablecandidates1.get(j),coeff);

            }
        }

        return goodcoeffcandidates;

    }

    public List<Candidate> hire (Map<Candidate, Double> finalcandidates, Vacancy vacancy) throws EmptyCandidateListexception {

            if (finalcandidates.isEmpty()) {
                throw new EmptyCandidateListexception("Candidate's list is empty!");
            }

            SortedSet<Map.Entry<Candidate,Double>> finalcandidatessorted = entriesSortedByValues(finalcandidates);
            System.out.println("Candidates with coefficient >50% sorted "+finalcandidatessorted);
            Iterator<Map.Entry<Candidate,Double>> candidatesiterator=finalcandidatessorted.iterator();
            List<Candidate> hired = new ArrayList<>();
            for(int i=0;i<vacancy.getCandidatesQuantity();i++) {
                if (candidatesiterator.hasNext()) {
                    Map.Entry<Candidate, Double> tmp = candidatesiterator.next();
                    System.out.println("candidate: "+ tmp.getKey()+" coefficient: "+tmp.getValue()+"\n");
                    hired.add(tmp.getKey());
                }
            }
            System.out.println("Hired candidates : "+hired.size() + " " +"for vacancy: "+ vacancy.getName() + " : "+hired);


            return hired;

    }


    private static <K,V extends Comparable<? super V>>
        SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}
