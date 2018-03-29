import entity.*;
import exception.EmptyCandidateListexception;
import logic.Recruitment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

import java.util.*;

/**
 * Created by Acer on 29.03.2018.
 */
@RunWith(Theories.class)
public class RecruitmentUnitTest {

    @DataPoint("positive")
    public static List<Candidate> candidatesPositive = Arrays.asList(
            new Candidate("Ivan", Arrays.asList(
                    new Competence(CompetenceNameType.IdeasGeneration, CompetencePriorityType.OBLIGATORY,4)
            ))
    );

    @DataPoint("negative")
    public static List<Candidate> candidatesNegative = Arrays.asList();

    @DataPoint("positive2")
    public static List<Candidate> candidatesPositive2 = Arrays.asList(
            new Candidate("Ivan", Arrays.asList(
                    new Competence(CompetenceNameType.IdeasGeneration, CompetencePriorityType.OBLIGATORY,4)
            )),
            new Candidate("Gleb", Arrays.asList(
                    new Competence(CompetenceNameType.Relations, CompetencePriorityType.OBLIGATORY,5)
            ))
    );

    private Recruitment recruitment;

    @Before
    public void setUp(){
        recruitment = new Recruitment();
    }

    @Test
    @Theory
    public void matchCompetancePositiveTest(@FromDataPoints("positive") List<Candidate> candidates) throws EmptyCandidateListexception{
        HashSet<Candidate> candidates1 = new HashSet<Candidate>();
        candidates1.addAll(candidates);
        Vacancy vacancy = new Vacancy("vacancy",1, Arrays.asList(
                new Competence(CompetenceNameType.IdeasGeneration,CompetencePriorityType.OBLIGATORY,4)
        ));
        Assert.assertEquals(recruitment.matchCompetence(vacancy, candidates),candidates1);
    }

    @Test(expected = EmptyCandidateListexception.class)
    @Theory
    public void matchCompetanceNegativeTest(@FromDataPoints("negative") List<Candidate> candidates) throws EmptyCandidateListexception{
        Vacancy vacancy = new Vacancy("vacancy",1, Arrays.asList(
                new Competence(CompetenceNameType.IdeasGeneration,CompetencePriorityType.OBLIGATORY,4)
        ));
        recruitment.matchCompetence(vacancy, candidates);
    }

    @Test
    @Theory
    public void calculatePositiveTest(@FromDataPoints("positive2") List<Candidate> candidates){
        Map<Candidate,Double> map = new HashMap<>();
        map = recruitment.calculate(new Vacancy("vacancy",6, Arrays.asList(
                new Competence(CompetenceNameType.IdeasGeneration,CompetencePriorityType.OBLIGATORY,5),
                new Competence(CompetenceNameType.Relations,CompetencePriorityType.OBLIGATORY,4)
        )), candidates);
        for (int i = 0; i<candidates.size();i++){
            if(map.containsKey(candidates.get(i))){
                Assert.assertEquals(map.containsKey(candidates.get(i)),true);
            }
        }
    }

}
