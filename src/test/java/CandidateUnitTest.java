import entity.Candidate;
import entity.Competence;
import entity.CompetenceNameType;
import entity.CompetencePriorityType;
import exception.CompetenceScaleException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import validation.CompetenceValidator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Acer on 29.03.2018.
 */
@RunWith(Theories.class)
public class CandidateUnitTest {

    @DataPoint("positive")
    public static List<Competence> competences = Arrays.asList(
            new Competence(CompetenceNameType.IdeasGeneration,CompetencePriorityType.NOPRIORITY,3)
    );

    private Candidate candidate;

    @Before
    public void setUp(){
        candidate = new Candidate("ivan", competences);
    }

    @Test
    @Theory
    public void createCandidateTest(@FromDataPoints("positive") List<Competence> competences){
        String surname = "Ivan";
        candidate.setSurname(surname);
        candidate.setCandidateCompetence(competences);
        Assert.assertEquals(candidate.getSurname(),surname);
        Assert.assertEquals(candidate.getCandidateCompetence(),competences);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeSurameTest(){
        candidate.setSurname("");
    }

}
