import entity.Competence;
import entity.CompetenceNameType;
import entity.CompetencePriorityType;
import entity.Vacancy;
import exception.ObligatoryCompetenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import validation.CompetenceValidator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
/**
 * Created by Acer on 28.03.2018.
 */
@RunWith(Theories.class)
public class VacancyUnitTest {

    @DataPoint("positive")
    public static List<Competence> competencePositive = Arrays.asList(
            new Competence(CompetenceNameType.IdeasGeneration,CompetencePriorityType.OBLIGATORY,1),
            new Competence(CompetenceNameType.Relations,CompetencePriorityType.OBLIGATORY,6)
    );

    @DataPoint("negative")
    public static List<Competence> noObligatoryCompetence = Arrays.asList(
            new Competence(CompetenceNameType.IdeasGeneration,CompetencePriorityType.NOPRIORITY,1),
            new Competence(CompetenceNameType.Relations,CompetencePriorityType.OPTIONAL,5)
    );

    private CompetenceValidator competenceValidator;
    private Vacancy vacancy;

    @Before
    public void setUp(){
        competenceValidator = new CompetenceValidator();
        vacancy = new Vacancy("vacancy", 5,competencePositive);
    }

    @Test
    @Theory
    public void createVacancy(@FromDataPoints("positive") List<Competence> competences) throws ObligatoryCompetenceException{
        String name = "vacancy";
        int quantity = 2;
        competenceValidator.checkObligatory(competences);
        vacancy = new Vacancy(name,quantity,competences);
        Assert.assertEquals(vacancy.getName(),name);
        Assert.assertEquals(vacancy.getCandidatesQuantity(),quantity);
        Assert.assertEquals(vacancy.getRequiredCompetence(),competences);
    }

    @Test(expected = ObligatoryCompetenceException.class)
    @Theory
    public void createVacancyWithoutObligatoryCompetance(@FromDataPoints("negative") List<Competence> competences) throws ObligatoryCompetenceException {
        competenceValidator.checkObligatory(competences);
        new Vacancy("vacancy",1,competences);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeQuantityTest(){
        vacancy.setCandidatesQuantity(-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeName(){
        vacancy.setName("");
    }
}
