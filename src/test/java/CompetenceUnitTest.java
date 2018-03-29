import entity.Candidate;
import entity.Competence;
import entity.CompetenceNameType;
import entity.CompetencePriorityType;
import exception.CompetenceScaleException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import validation.CompetenceValidator;

import java.util.Arrays;
import java.util.List;
/**
 * Created by Acer on 28.03.2018.
 */

@RunWith(Theories.class)
public class CompetenceUnitTest {

     @DataPoints("positive")
     public static List<Integer> levelPositive = Arrays.asList(1, 5, 10);

     @DataPoints("negative")
     public static List<Integer> levelNegative = Arrays.asList(-1, 11);

     private Competence competence;
     private CompetenceValidator competenceValidator;

     @Before
     public void setUp() {
         competence = new Competence();
         competenceValidator = new CompetenceValidator();
     }

     @Test
     @Theory
     public void setPositiveLevelTest(@FromDataPoints("positive") int level) throws CompetenceScaleException{
         competence.setLevel(level);
         competenceValidator.checkLevel(level);
         Assert.assertEquals(competenceValidator.checkLevel(level),true);
     }

     @Test(expected = CompetenceScaleException.class)
     @Theory
     public void setNegativeLevelTest(@FromDataPoints("negative") int level) throws CompetenceScaleException{
         competence.setLevel(level);
         competenceValidator.checkLevel(level);
     }

}
